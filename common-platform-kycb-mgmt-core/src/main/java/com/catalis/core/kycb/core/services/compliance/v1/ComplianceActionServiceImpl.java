package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.compliance.v1.ComplianceActionMapper;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceActionDTO;
import com.catalis.core.kycb.models.entities.compliance.v1.ComplianceAction;
import com.catalis.core.kycb.models.repositories.compliance.v1.ComplianceActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the compliance action service.
 */
@Service
@Transactional
public class ComplianceActionServiceImpl implements ComplianceActionService {

    @Autowired
    private ComplianceActionRepository repository;

    @Autowired
    private ComplianceActionMapper mapper;

    @Override
    public Mono<PaginationResponse<ComplianceActionDTO>> findAll(FilterRequest<ComplianceActionDTO> filterRequest) {
        return FilterUtils.createFilter(
                ComplianceAction.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ComplianceActionDTO> create(ComplianceActionDTO dto) {
        ComplianceAction entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ComplianceActionDTO> getById(Long actionId) {
        return repository.findById(actionId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ComplianceActionDTO> update(Long actionId, ComplianceActionDTO dto) {
        return repository.findById(actionId)
                .flatMap(existingEntity -> {
                    ComplianceAction updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setComplianceActionId(actionId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long actionId) {
        return repository.deleteById(actionId);
    }
}
