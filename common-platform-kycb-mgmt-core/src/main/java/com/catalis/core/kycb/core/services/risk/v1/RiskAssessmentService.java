package com.catalis.core.kycb.core.services.risk.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.risk.v1.RiskAssessmentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for risk assessment operations.
 */
public interface RiskAssessmentService {
    /**
     * Retrieves all risk assessments based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving risk assessments.
     * @return A {@link Mono} containing a paginated response of risk assessment DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<RiskAssessmentDTO>> findAll(FilterRequest<RiskAssessmentDTO> filterRequest);
    
    /**
     * Retrieves all risk assessments for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of risk assessment DTOs.
     */
    Flux<RiskAssessmentDTO> findByPartyId(Long partyId);
    
    /**
     * Creates a new risk assessment entry based on the provided data transfer object.
     *
     * @param dto The RiskAssessmentDTO containing data to create a new risk assessment record
     * @return A Mono containing the created RiskAssessmentDTO
     */
    Mono<RiskAssessmentDTO> create(RiskAssessmentDTO dto);
    
    /**
     * Retrieves a RiskAssessmentDTO by its unique identifier.
     *
     * @param riskAssessmentId The ID of the RiskAssessment to retrieve.
     * @return A Mono containing the RiskAssessmentDTO if found, otherwise an empty mono.
     */
    Mono<RiskAssessmentDTO> getById(Long riskAssessmentId);
    
    /**
     * Updates an existing RiskAssessment entry with new data provided in the DTO.
     *
     * @param riskAssessmentId The ID of the RiskAssessment to be updated.
     * @param dto A DTO containing the fields to update for the RiskAssessment.
     * @return A Mono containing the updated RiskAssessmentDTO if successful.
     */
    Mono<RiskAssessmentDTO> update(Long riskAssessmentId, RiskAssessmentDTO dto);
    
    /**
     * Calculates a risk score for a party based on various risk factors.
     *
     * @param partyId The ID of the party to assess.
     * @param assessmentType The type of assessment to perform.
     * @return A Mono containing the RiskAssessmentDTO with the calculated risk score.
     */
    Mono<RiskAssessmentDTO> calculateRiskScore(Long partyId, String assessmentType);
    
    /**
     * Retrieves the latest risk assessment for a party.
     *
     * @param partyId The ID of the party.
     * @return A Mono containing the latest RiskAssessmentDTO.
     */
    Mono<RiskAssessmentDTO> getLatestByPartyId(Long partyId);
}