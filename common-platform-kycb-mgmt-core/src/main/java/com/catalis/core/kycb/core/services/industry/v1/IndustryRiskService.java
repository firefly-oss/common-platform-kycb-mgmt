package com.catalis.core.kycb.core.services.industry.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Service interface for industry risk operations.
 */
public interface IndustryRiskService {
    /**
     * Retrieves all industry risks based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving industry risks.
     * @return A {@link Mono} containing a paginated response of industry risk DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<IndustryRiskDTO>> findAll(FilterRequest<IndustryRiskDTO> filterRequest);
    
    /**
     * Creates a new industry risk entry based on the provided data transfer object.
     *
     * @param dto The IndustryRiskDTO containing data to create a new industry risk record
     * @return A Mono containing the created IndustryRiskDTO
     */
    Mono<IndustryRiskDTO> create(IndustryRiskDTO dto);
    
    /**
     * Retrieves an IndustryRiskDTO by its unique identifier.
     *
     * @param industryRiskId The ID of the IndustryRisk to retrieve.
     * @return A Mono containing the IndustryRiskDTO if found, otherwise an empty mono.
     */
    Mono<IndustryRiskDTO> getById(Long industryRiskId);
    
    /**
     * Retrieves an IndustryRiskDTO by its activity code.
     *
     * @param activityCode The activity code of the IndustryRisk to retrieve.
     * @return A Mono containing the IndustryRiskDTO if found, otherwise an empty mono.
     */
    Mono<IndustryRiskDTO> getByActivityCode(String activityCode);
    
    /**
     * Updates an existing IndustryRisk entry with new data provided in the DTO.
     *
     * @param industryRiskId The ID of the IndustryRisk to be updated.
     * @param dto A DTO containing the fields to update for the IndustryRisk.
     * @return A Mono containing the updated IndustryRiskDTO if successful.
     */
    Mono<IndustryRiskDTO> update(Long industryRiskId, IndustryRiskDTO dto);
    
    /**
     * Updates an existing IndustryRisk entry by activity code with new data provided in the DTO.
     *
     * @param activityCode The activity code of the IndustryRisk to be updated.
     * @param dto A DTO containing the fields to update for the IndustryRisk.
     * @return A Mono containing the updated IndustryRiskDTO if successful.
     */
    Mono<IndustryRiskDTO> updateByActivityCode(String activityCode, IndustryRiskDTO dto);
    
    /**
     * Deletes an Industry Risk by its ID.
     *
     * @param industryRiskId The ID of the Industry Risk to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long industryRiskId);
    
    /**
     * Retrieves all industry risks with a specific inherent risk level.
     *
     * @param riskLevel The inherent risk level.
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findByInherentRiskLevel(String riskLevel);
    
    /**
     * Retrieves all industry risks with a risk score above a threshold.
     *
     * @param threshold The risk score threshold.
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findByRiskScoreAbove(Integer threshold);
    
    /**
     * Retrieves all industry risks that are high risk according to SEPBLAC.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findSepblacHighRisk();
    
    /**
     * Retrieves all industry risks that are high risk according to EU.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findEuHighRisk();
    
    /**
     * Retrieves all industry risks that are high risk according to FATF.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findFatfHighRisk();
    
    /**
     * Retrieves all industry risks that are cash intensive.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findCashIntensive();
    
    /**
     * Retrieves all industry risks that involve complex structures.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findComplexStructures();
    
    /**
     * Retrieves all industry risks that require enhanced due diligence.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findRequiresEdd();
    
    /**
     * Retrieves all industry risks that need reassessment.
     *
     * @return A {@link Flux} of industry risk DTOs.
     */
    Flux<IndustryRiskDTO> findNeedsReassessment();
    
    /**
     * Assesses an industry risk.
     *
     * @param industryRiskId The ID of the industry risk to assess.
     * @param assessedBy The user assessing the risk.
     * @param riskScore The risk score.
     * @param riskLevel The risk level.
     * @param riskFactors Factors contributing to the risk.
     * @param mitigatingFactors Factors mitigating the risk.
     * @return A Mono containing the updated IndustryRiskDTO.
     */
    Mono<IndustryRiskDTO> assessRisk(Long industryRiskId, String assessedBy, Integer riskScore, String riskLevel, String riskFactors, String mitigatingFactors);
    
    /**
     * Schedules the next assessment for an industry risk.
     *
     * @param industryRiskId The ID of the industry risk to schedule.
     * @param nextAssessmentDate The date of the next assessment.
     * @return A Mono containing the updated IndustryRiskDTO.
     */
    Mono<IndustryRiskDTO> scheduleNextAssessment(Long industryRiskId, LocalDateTime nextAssessmentDate);
}