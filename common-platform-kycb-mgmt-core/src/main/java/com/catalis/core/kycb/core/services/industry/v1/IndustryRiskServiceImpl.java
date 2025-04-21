package com.catalis.core.kycb.core.services.industry.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.industry.v1.IndustryRiskMapper;
import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.models.entities.industry.v1.IndustryRisk;
import com.catalis.core.kycb.models.repositories.industry.v1.IndustryRiskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
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
    public Mono<IndustryRiskDTO> getByActivityCode(String activityCode) {
        return repository.findFirstByActivityCodeOrderByAssessmentDateDesc(activityCode)
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
    public Mono<IndustryRiskDTO> updateByActivityCode(String activityCode, IndustryRiskDTO dto) {
        return repository.findFirstByActivityCodeOrderByAssessmentDateDesc(activityCode)
                .flatMap(existingEntity -> {
                    IndustryRisk updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setIndustryRiskId(existingEntity.getIndustryRiskId());
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

    @Override
    public Flux<IndustryRiskDTO> findByInherentRiskLevel(String riskLevel) {
        return repository.findByInherentRiskLevel(RiskLevelEnum.valueOf(riskLevel))
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findByRiskScoreAbove(Integer threshold) {
        return repository.findByRiskScoreGreaterThanEqual(threshold)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findSepblacHighRisk() {
        return repository.findBySepblacHighRisk(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findEuHighRisk() {
        return repository.findByEuHighRisk(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findFatfHighRisk() {
        return repository.findByFatfHighRisk(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findCashIntensive() {
        return repository.findByCashIntensive(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findComplexStructures() {
        return repository.findByComplexStructures(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findRequiresEdd() {
        return repository.findByRequiresEdd(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<IndustryRiskDTO> findNeedsReassessment() {
        return repository.findByNextAssessmentDateBefore(LocalDateTime.now())
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IndustryRiskDTO> assessRisk(Long industryRiskId, String assessedBy, Integer riskScore, String riskLevel, String riskFactors, String mitigatingFactors) {
        return repository.findById(industryRiskId)
                .flatMap(entity -> {
                    entity.setAssessedBy(assessedBy);
                    entity.setRiskScore(riskScore);
                    entity.setInherentRiskLevel(RiskLevelEnum.valueOf(riskLevel));
                    entity.setRiskFactors(riskFactors);
                    entity.setMitigatingFactors(mitigatingFactors);
                    entity.setAssessmentDate(LocalDateTime.now());
                    
                    // Set requiresEdd based on risk level
                    if (RiskLevelEnum.valueOf(riskLevel) == RiskLevelEnum.HIGH || 
                        RiskLevelEnum.valueOf(riskLevel) == RiskLevelEnum.EXTREME) {
                        entity.setRequiresEdd(true);
                    }
                    
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<IndustryRiskDTO> scheduleNextAssessment(Long industryRiskId, LocalDateTime nextAssessmentDate) {
        return repository.findById(industryRiskId)
                .flatMap(entity -> {
                    entity.setNextAssessmentDate(nextAssessmentDate);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }
}