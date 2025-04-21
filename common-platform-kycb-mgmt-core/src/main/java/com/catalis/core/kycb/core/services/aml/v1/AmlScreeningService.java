package com.catalis.core.kycb.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for AML screening operations.
 */
public interface AmlScreeningService {
    /**
     * Retrieves all AML screenings based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving AML screenings.
     * @return A {@link Mono} containing a paginated response of AML screening DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<AmlScreeningDTO>> findAll(FilterRequest<AmlScreeningDTO> filterRequest);
    
    /**
     * Retrieves all AML screenings for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of AML screening DTOs.
     */
    Flux<AmlScreeningDTO> findByPartyId(Long partyId);
    
    /**
     * Creates a new AML screening entry based on the provided data transfer object.
     *
     * @param dto The AmlScreeningDTO containing data to create a new AML screening record
     * @return A Mono containing the created AmlScreeningDTO
     */
    Mono<AmlScreeningDTO> create(AmlScreeningDTO dto);
    
    /**
     * Retrieves an AmlScreeningDTO by its unique identifier.
     *
     * @param amlScreeningId The ID of the AmlScreening to retrieve.
     * @return A Mono containing the AmlScreeningDTO if found, otherwise an empty mono.
     */
    Mono<AmlScreeningDTO> getById(Long amlScreeningId);
    
    /**
     * Updates an existing AmlScreening entry with new data provided in the DTO.
     *
     * @param amlScreeningId The ID of the AmlScreening to be updated.
     * @param dto A DTO containing the fields to update for the AmlScreening.
     * @return A Mono containing the updated AmlScreeningDTO if successful.
     */
    Mono<AmlScreeningDTO> update(Long amlScreeningId, AmlScreeningDTO dto);
    
    /**
     * Executes a new AML screening for a party.
     *
     * @param partyId The ID of the party to screen.
     * @param screeningType The type of screening to perform.
     * @return A Mono containing the AmlScreeningDTO with the screening results.
     */
    Mono<AmlScreeningDTO> executeScreening(Long partyId, String screeningType);
    
    /**
     * Retrieves the latest AML screening for a party.
     *
     * @param partyId The ID of the party.
     * @return A Mono containing the latest AmlScreeningDTO.
     */
    Mono<AmlScreeningDTO> getLatestByPartyId(Long partyId);
}