package com.catalis.core.kycb.core.services.expected.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.expected.v1.ExpectedActivityDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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
     * Retrieves all expected activities for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findByPartyId(Long partyId);
    
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
    
    /**
     * Retrieves all expected activities of a specific type.
     *
     * @param activityTypeCode The type code of the expected activities to retrieve.
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findByActivityTypeCode(String activityTypeCode);
    
    /**
     * Retrieves all expected activities with monthly volume above a threshold.
     *
     * @param threshold The monthly volume threshold.
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findByMonthlyVolumeAbove(BigDecimal threshold);
    
    /**
     * Retrieves all expected activities with annual volume above a threshold.
     *
     * @param threshold The annual volume threshold.
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findByAnnualVolumeAbove(BigDecimal threshold);
    
    /**
     * Retrieves all expected activities with transaction count above a threshold.
     *
     * @param threshold The transaction count threshold.
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findByTransactionCountAbove(Integer threshold);
    
    /**
     * Retrieves all high-value expected activities.
     *
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findHighValueActivities();
    
    /**
     * Retrieves all cash-intensive expected activities.
     *
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findCashIntensiveActivities();
    
    /**
     * Retrieves all expected activities with tax haven transactions.
     *
     * @return A {@link Flux} of expected activity DTOs.
     */
    Flux<ExpectedActivityDTO> findTaxHavenActivities();
    
    /**
     * Retrieves the latest expected activity for a party.
     *
     * @param partyId The ID of the party.
     * @return A Mono containing the latest ExpectedActivityDTO.
     */
    Mono<ExpectedActivityDTO> getLatestByPartyId(Long partyId);
}