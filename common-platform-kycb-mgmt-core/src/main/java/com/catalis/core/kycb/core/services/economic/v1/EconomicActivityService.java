package com.catalis.core.kycb.core.services.economic.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.economic.v1.EconomicActivityDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for economic activity operations.
 */
public interface EconomicActivityService {
    /**
     * Retrieves all economic activities based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving economic activities.
     * @return A {@link Mono} containing a paginated response of economic activity DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<EconomicActivityDTO>> findAll(FilterRequest<EconomicActivityDTO> filterRequest);

    /**
     * Retrieves all economic activities for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of economic activity DTOs.
     */
    Flux<EconomicActivityDTO> findByPartyId(Long partyId);

    /**
     * Creates a new economic activity entry based on the provided data transfer object.
     *
     * @param dto The EconomicActivityDTO containing data to create a new economic activity record
     * @return A Mono containing the created EconomicActivityDTO
     */
    Mono<EconomicActivityDTO> create(EconomicActivityDTO dto);

    /**
     * Retrieves an EconomicActivityDTO by its unique identifier.
     *
     * @param activityId The ID of the EconomicActivity to retrieve.
     * @return A Mono containing the EconomicActivityDTO if found, otherwise an empty mono.
     */
    Mono<EconomicActivityDTO> getById(Long activityId);

    /**
     * Updates an existing EconomicActivity entry with new data provided in the DTO.
     *
     * @param activityId The ID of the EconomicActivity to be updated.
     * @param dto A DTO containing the fields to update for the EconomicActivity.
     * @return A Mono containing the updated EconomicActivityDTO if successful.
     */
    Mono<EconomicActivityDTO> update(Long activityId, EconomicActivityDTO dto);

    /**
     * Deletes an Economic Activity by its ID.
     *
     * @param activityId The ID of the Economic Activity to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long activityId);

    /**
     * Retrieves all economic activities with a specific activity code.
     *
     * @param activityCode The activity code to search for.
     * @return A {@link Flux} of economic activity DTOs.
     */
    Flux<EconomicActivityDTO> findByActivityCode(String activityCode);

    /**
     * Retrieves all economic activities with a specific risk level.
     *
     * @param riskLevel The risk level to search for.
     * @return A {@link Flux} of economic activity DTOs.
     */
    Flux<EconomicActivityDTO> findByRiskLevel(String riskLevel);

    /**
     * Sets an economic activity as the primary activity for a party.
     *
     * @param activityId The ID of the economic activity to set as primary.
     * @return A Mono containing the updated EconomicActivityDTO.
     */
    Mono<EconomicActivityDTO> setPrimaryActivity(Long activityId);
}
