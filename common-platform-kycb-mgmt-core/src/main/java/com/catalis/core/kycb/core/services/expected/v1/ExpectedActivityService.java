package com.catalis.core.kycb.core.services.expected.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.expected.v1.ExpectedActivityDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for expected activity operations.
 */
public interface ExpectedActivityService {
    /**
     * Retrieves all expected activities based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving expected activities.
     * @return A {@link Mono} containing a paginated response of expected activity DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<ExpectedActivityDTO>> findAll(FilterRequest<ExpectedActivityDTO> filterRequest);

    /**
     * Creates a new expected activity entry based on the provided data transfer object.
     *
     * @param dto The ExpectedActivityDTO containing data to create a new expected activity record
     * @return A Mono containing the created ExpectedActivityDTO
     */
    Mono<ExpectedActivityDTO> create(ExpectedActivityDTO dto);

    /**
     * Retrieves an ExpectedActivityDTO by its unique identifier.
     *
     * @param activityId The ID of the ExpectedActivity to retrieve.
     * @return A Mono containing the ExpectedActivityDTO if found, otherwise an empty mono.
     */
    Mono<ExpectedActivityDTO> getById(Long activityId);

    /**
     * Updates an existing ExpectedActivity entry with new data provided in the DTO.
     *
     * @param activityId The ID of the ExpectedActivity to be updated.
     * @param dto A DTO containing the fields to update for the ExpectedActivity.
     * @return A Mono containing the updated ExpectedActivityDTO if successful.
     */
    Mono<ExpectedActivityDTO> update(Long activityId, ExpectedActivityDTO dto);

    /**
     * Deletes an Expected Activity by its ID.
     *
     * @param activityId The ID of the Expected Activity to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long activityId);
}
