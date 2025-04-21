package com.catalis.core.kycb.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlMatchDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for AML match operations.
 */
public interface AmlMatchService {
    /**
     * Retrieves all AML matches based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving AML matches.
     * @return A {@link Mono} containing a paginated response of AML match DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<AmlMatchDTO>> findAll(FilterRequest<AmlMatchDTO> filterRequest);
    
    /**
     * Retrieves all AML matches for a specific screening.
     *
     * @param amlScreeningId The ID of the AML screening.
     * @return A {@link Flux} of AML match DTOs.
     */
    Flux<AmlMatchDTO> findByAmlScreeningId(Long amlScreeningId);
    
    /**
     * Creates a new AML match entry based on the provided data transfer object.
     *
     * @param dto The AmlMatchDTO containing data to create a new AML match record
     * @return A Mono containing the created AmlMatchDTO
     */
    Mono<AmlMatchDTO> create(AmlMatchDTO dto);
    
    /**
     * Retrieves an AmlMatchDTO by its unique identifier.
     *
     * @param amlMatchId The ID of the AmlMatch to retrieve.
     * @return A Mono containing the AmlMatchDTO if found, otherwise an empty mono.
     */
    Mono<AmlMatchDTO> getById(Long amlMatchId);
    
    /**
     * Updates an existing AmlMatch entry with new data provided in the DTO.
     *
     * @param amlMatchId The ID of the AmlMatch to be updated.
     * @param dto A DTO containing the fields to update for the AmlMatch.
     * @return A Mono containing the updated AmlMatchDTO if successful.
     */
    Mono<AmlMatchDTO> update(Long amlMatchId, AmlMatchDTO dto);
    
    /**
     * Updates the resolution status of an AML match.
     *
     * @param amlMatchId The ID of the AML match to update.
     * @param resolutionStatus The new resolution status.
     * @param resolutionNotes Notes explaining the resolution.
     * @param resolutionAgent The agent who resolved the match.
     * @return A Mono containing the updated AmlMatchDTO.
     */
    Mono<AmlMatchDTO> updateResolution(Long amlMatchId, String resolutionStatus, String resolutionNotes, String resolutionAgent);
}