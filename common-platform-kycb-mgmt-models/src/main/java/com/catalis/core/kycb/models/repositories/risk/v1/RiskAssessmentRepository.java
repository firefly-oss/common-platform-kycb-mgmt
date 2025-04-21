package com.catalis.core.kycb.models.repositories.risk.v1;

import com.catalis.core.kycb.models.entities.risk.v1.RiskAssessment;
import com.catalis.core.kycb.interfaces.enums.assessment.v1.AssessmentTypeEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskCategoryEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for risk assessment operations.
 */
@Repository
public interface RiskAssessmentRepository extends BaseRepository<RiskAssessment, Long> {

    /**
     * Find risk assessments by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByPartyId(Long partyId);

    /**
     * Find risk assessments by assessment type.
     *
     * @param assessmentTypeEnum The type of assessment
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByAssessmentType(AssessmentTypeEnum assessmentTypeEnum);

    /**
     * Find risk assessments by risk category.
     *
     * @param riskCategoryEnum The risk category
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByRiskCategory(RiskCategoryEnum riskCategoryEnum);

    /**
     * Find risk assessments by risk level.
     *
     * @param riskLevelEnum The risk level
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByRiskLevel(RiskLevelEnum riskLevelEnum);

    /**
     * Find risk assessments by assessment date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByAssessmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find risk assessments by assessment agent.
     *
     * @param assessmentAgent The agent who performed the assessment
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByAssessmentAgent(String assessmentAgent);

    /**
     * Find risk assessments that need to be reassessed.
     *
     * @param currentDate The current date
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByNextAssessmentDateBefore(LocalDateTime currentDate);

    /**
     * Find risk assessments with a score above a threshold.
     *
     * @param threshold The score threshold
     * @return A flux of risk assessments
     */
    Flux<RiskAssessment> findByRiskScoreGreaterThanEqual(Integer threshold);

    /**
     * Find the latest risk assessment for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest risk assessment
     */
    Mono<RiskAssessment> findFirstByPartyIdOrderByAssessmentDateDesc(Long partyId);
}