package com.catalis.core.kycb.models.repositories.industry.v1;

import com.catalis.core.kycb.models.entities.industry.v1.IndustryRisk;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for industry risk operations.
 */
@Repository
public interface IndustryRiskRepository extends BaseRepository<IndustryRisk, Long> {

    /**
     * Find industry risks by activity code.
     *
     * @param activityCode The activity code
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByActivityCode(String activityCode);

    /**
     * Find industry risks by industry name.
     *
     * @param industryName The industry name
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByIndustryName(String industryName);

    /**
     * Find industry risks by inherent risk level.
     *
     * @param inherentRiskLevelEnum The inherent risk level
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByInherentRiskLevel(RiskLevelEnum inherentRiskLevelEnum);

    /**
     * Find industry risks with risk score above a threshold.
     *
     * @param threshold The risk score threshold
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByRiskScoreGreaterThanEqual(Integer threshold);

    /**
     * Find industry risks by SEPBLAC high-risk status.
     *
     * @param sepblacHighRisk The SEPBLAC high-risk flag
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findBySepblacHighRisk(Boolean sepblacHighRisk);

    /**
     * Find industry risks by EU high-risk status.
     *
     * @param euHighRisk The EU high-risk flag
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByEuHighRisk(Boolean euHighRisk);

    /**
     * Find industry risks by FATF high-risk status.
     *
     * @param fatfHighRisk The FATF high-risk flag
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByFatfHighRisk(Boolean fatfHighRisk);

    /**
     * Find industry risks by cash-intensive status.
     *
     * @param cashIntensive The cash-intensive flag
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByCashIntensive(Boolean cashIntensive);

    /**
     * Find industry risks by complex structures status.
     *
     * @param complexStructures The complex structures flag
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByComplexStructures(Boolean complexStructures);

    /**
     * Find industry risks by assessment date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByAssessmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find industry risks by assessed by.
     *
     * @param assessedBy The user who assessed the risk
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByAssessedBy(String assessedBy);

    /**
     * Find industry risks that need reassessment.
     *
     * @param currentDate The current date
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByNextAssessmentDateBefore(LocalDateTime currentDate);

    /**
     * Find industry risks by EDD requirement status.
     *
     * @param requiresEdd The EDD requirement flag
     * @return A flux of industry risks
     */
    Flux<IndustryRisk> findByRequiresEdd(Boolean requiresEdd);

    /**
     * Find the latest industry risk for an activity code.
     *
     * @param activityCode The activity code
     * @return A mono with the latest industry risk
     */
    Mono<IndustryRisk> findFirstByActivityCodeOrderByAssessmentDateDesc(String activityCode);
}