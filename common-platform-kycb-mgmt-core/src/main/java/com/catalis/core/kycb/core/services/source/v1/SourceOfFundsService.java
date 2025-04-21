package com.catalis.core.kycb.core.services.source.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.source.v1.SourceOfFundsDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for source of funds operations.
 */
public interface SourceOfFundsService {
    /**
     * Retrieves all sources of funds based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving sources of funds.
     * @return A {@link Mono} containing a paginated response of source of funds DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<SourceOfFundsDTO>> findAll(FilterRequest<SourceOfFundsDTO> filterRequest);
    
    /**
     * Retrieves all sources of funds for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of source of funds DTOs.
     */
    Flux<SourceOfFundsDTO> findByPartyId(Long partyId);
    
    /**
     * Creates a new source of funds entry based on the provided data transfer object.
     *
     * @param dto The SourceOfFundsDTO containing data to create a new source of funds record
     * @return A Mono containing the created SourceOfFundsDTO
     */
    Mono<SourceOfFundsDTO> create(SourceOfFundsDTO dto);
    
    /**
     * Retrieves a SourceOfFundsDTO by its unique identifier.
     *
     * @param sourceId The ID of the SourceOfFunds to retrieve.
     * @return A Mono containing the SourceOfFundsDTO if found, otherwise an empty mono.
     */
    Mono<SourceOfFundsDTO> getById(Long sourceId);
    
    /**
     * Updates an existing SourceOfFunds entry with new data provided in the DTO.
     *
     * @param sourceId The ID of the SourceOfFunds to be updated.
     * @param dto A DTO containing the fields to update for the SourceOfFunds.
     * @return A Mono containing the updated SourceOfFundsDTO if successful.
     */
    Mono<SourceOfFundsDTO> update(Long sourceId, SourceOfFundsDTO dto);
    
    /**
     * Deletes a Source of Funds by its ID.
     *
     * @param sourceId The ID of the Source of Funds to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long sourceId);
    
    /**
     * Retrieves all sources of funds of a specific type.
     *
     * @param sourceType The type of the sources of funds to retrieve.
     * @return A {@link Flux} of source of funds DTOs.
     */
    Flux<SourceOfFundsDTO> findBySourceType(String sourceType);
    
    /**
     * Verifies a source of funds.
     *
     * @param sourceId The ID of the source of funds to verify.
     * @param verificationNotes Notes explaining the verification.
     * @return A Mono containing the updated SourceOfFundsDTO.
     */
    Mono<SourceOfFundsDTO> verifySource(Long sourceId, String verificationNotes);
    
    /**
     * Sets a source of funds as the primary source for a party.
     *
     * @param sourceId The ID of the source of funds to set as primary.
     * @return A Mono containing the updated SourceOfFundsDTO.
     */
    Mono<SourceOfFundsDTO> setPrimarySource(Long sourceId);
}