package com.catalis.core.kycb.core.services.risk.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.risk.v1.RiskAssessmentMapper;
import com.catalis.core.kycb.interfaces.dtos.risk.v1.RiskAssessmentDTO;
import com.catalis.core.kycb.interfaces.enums.assessment.v1.AssessmentTypeEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskCategoryEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.models.entities.risk.v1.RiskAssessment;
import com.catalis.core.kycb.models.repositories.risk.v1.RiskAssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Random;

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
    public Flux<RiskAssessmentDTO> findByPartyId(Long partyId) {
        return repository.findByPartyId(partyId)
                .map(mapper::toDTO);
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
    public Mono<RiskAssessmentDTO> calculateRiskScore(Long partyId, String assessmentType) {
        // In a real implementation, this would calculate a risk score based on various factors
        // For now, we'll create a simulated risk assessment
        
        RiskAssessment assessment = new RiskAssessment();
        assessment.setPartyId(partyId);
        assessment.setAssessmentDate(LocalDateTime.now());
        assessment.setAssessmentType(AssessmentTypeEnum.valueOf(assessmentType));
        
        // Simulate random risk score between 0 and 100
        Random random = new Random();
        int riskScore = random.nextInt(101);
        assessment.setRiskScore(riskScore);
        
        // Determine risk level based on score
        RiskLevelEnum riskLevel;
        if (riskScore < 25) {
            riskLevel = RiskLevelEnum.LOW;
        } else if (riskScore < 50) {
            riskLevel = RiskLevelEnum.MEDIUM;
        } else if (riskScore < 75) {
            riskLevel = RiskLevelEnum.HIGH;
        } else {
            riskLevel = RiskLevelEnum.EXTREME;
        }
        assessment.setRiskLevel(riskLevel);
        
        // Set risk category
        assessment.setRiskCategory(RiskCategoryEnum.CUSTOMER);
        
        // Set assessment notes
        assessment.setAssessmentNotes("Automated risk assessment generated on " + LocalDateTime.now());
        
        // Set next assessment date to 1 year from now
        assessment.setNextAssessmentDate(LocalDateTime.now().plusYears(1));
        
        return repository.save(assessment)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RiskAssessmentDTO> getLatestByPartyId(Long partyId) {
        return repository.findFirstByPartyIdOrderByAssessmentDateDesc(partyId)
                .map(mapper::toDTO);
    }
}