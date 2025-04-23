package com.catalis.core.kycb.core.services.industry.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.industry.v1.IndustryRiskMapper;
import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import com.catalis.core.kycb.models.entities.industry.v1.IndustryRisk;
import com.catalis.core.kycb.models.repositories.industry.v1.IndustryRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the industry risk service.
 */
@Service
@Transactional
public class IndustryRiskServiceImpl implements IndustryRiskService {

    @Autowired
    private IndustryRiskRepository repository;

    @Autowired
    private IndustryRiskMapper mapper;

    @Override
    public Mono<PaginationResponse<IndustryRiskDTO>> findAll(FilterRequest<IndustryRiskDTO> filterRequest) {
        return FilterUtils.createFilter(
                IndustryRisk.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<IndustryRiskDTO> create(IndustryRiskDTO dto) {
        IndustryRisk entity = mapper.toEntity(dto);
        // Set assessment date to now if not provided
        if (entity.getAssessmentDate() == null) {
            entity.setAssessmentDate(LocalDateTime.now());
        }
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IndustryRiskDTO> getById(Long industryRiskId) {
        return repository.findById(industryRiskId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IndustryRiskDTO> update(Long industryRiskId, IndustryRiskDTO dto) {
        return repository.findById(industryRiskId)
                .flatMap(existingEntity -> {
                    IndustryRisk updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setIndustryRiskId(industryRiskId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long industryRiskId) {
        return repository.deleteById(industryRiskId);
    }
}