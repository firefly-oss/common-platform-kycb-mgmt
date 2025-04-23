package com.catalis.core.kycb.core.services.risk.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.risk.v1.RiskAssessmentMapper;
import com.catalis.core.kycb.interfaces.dtos.risk.v1.RiskAssessmentDTO;
import com.catalis.core.kycb.models.entities.risk.v1.RiskAssessment;
import com.catalis.core.kycb.models.repositories.risk.v1.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the risk assessment service.
 */
@Service
@Transactional
public class RiskAssessmentServiceImpl implements RiskAssessmentService {

    @Autowired
    private RiskAssessmentRepository repository;

    @Autowired
    private RiskAssessmentMapper mapper;

    @Override
    public Mono<PaginationResponse<RiskAssessmentDTO>> findAll(FilterRequest<RiskAssessmentDTO> filterRequest) {
        return FilterUtils.createFilter(
                RiskAssessment.class,
                mapper::toDTO
        ).filter(filterRequest);
    }


    @Override
    public Mono<RiskAssessmentDTO> create(RiskAssessmentDTO dto) {
        RiskAssessment entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RiskAssessmentDTO> getById(Long riskAssessmentId) {
        return repository.findById(riskAssessmentId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RiskAssessmentDTO> update(Long riskAssessmentId, RiskAssessmentDTO dto) {
        return repository.findById(riskAssessmentId)
                .flatMap(existingEntity -> {
                    RiskAssessment updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setRiskAssessmentId(riskAssessmentId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long riskAssessmentId) {
        return repository.deleteById(riskAssessmentId);
    }
}
