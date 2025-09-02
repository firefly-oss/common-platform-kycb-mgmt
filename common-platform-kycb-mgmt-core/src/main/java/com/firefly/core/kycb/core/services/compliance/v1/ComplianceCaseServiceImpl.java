package com.firefly.core.kycb.core.services.compliance.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.core.mappers.compliance.v1.ComplianceCaseMapper;
import com.firefly.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
import com.firefly.core.kycb.models.entities.compliance.v1.ComplianceCase;
import com.firefly.core.kycb.models.repositories.compliance.v1.ComplianceCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

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
    public Mono<ComplianceCaseDTO> getById(UUID caseId) {
        return repository.findById(caseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ComplianceCaseDTO> update(UUID caseId, ComplianceCaseDTO dto) {
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
    public Mono<Void> delete(UUID caseId) {
        return repository.deleteById(caseId);
    }
}
