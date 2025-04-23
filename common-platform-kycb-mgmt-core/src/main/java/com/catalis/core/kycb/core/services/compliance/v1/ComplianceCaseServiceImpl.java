package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.compliance.v1.ComplianceCaseMapper;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
import com.catalis.core.kycb.models.entities.compliance.v1.ComplianceCase;
import com.catalis.core.kycb.models.repositories.compliance.v1.ComplianceCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the compliance case service.
 */
@Service
@Transactional
public class ComplianceCaseServiceImpl implements ComplianceCaseService {

    @Autowired
    private ComplianceCaseRepository repository;

    @Autowired
    private ComplianceCaseMapper mapper;

    @Override
    public Mono<PaginationResponse<ComplianceCaseDTO>> findAll(FilterRequest<ComplianceCaseDTO> filterRequest) {
        return FilterUtils.createFilter(
                ComplianceCase.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ComplianceCaseDTO> create(ComplianceCaseDTO dto) {
        ComplianceCase entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ComplianceCaseDTO> getById(Long caseId) {
        return repository.findById(caseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ComplianceCaseDTO> update(Long caseId, ComplianceCaseDTO dto) {
        return repository.findById(caseId)
                .flatMap(existingEntity -> {
                    ComplianceCase updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setComplianceCaseId(caseId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long caseId) {
        return repository.deleteById(caseId);
    }
}
