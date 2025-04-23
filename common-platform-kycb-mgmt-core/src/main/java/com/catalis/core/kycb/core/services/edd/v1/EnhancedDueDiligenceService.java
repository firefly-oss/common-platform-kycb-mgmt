package com.catalis.core.kycb.core.services.edd.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.edd.v1.EnhancedDueDiligenceDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for enhanced due diligence operations.
 */
public interface EnhancedDueDiligenceService {
    /**
     * Retrieves all enhanced due diligence records based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving EDD records.
     * @return A {@link Mono} containing a paginated response of EDD DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<EnhancedDueDiligenceDTO>> findAll(FilterRequest<EnhancedDueDiligenceDTO> filterRequest);

    /**
     * Creates a new enhanced due diligence entry based on the provided data transfer object.
     *
     * @param dto The EnhancedDueDiligenceDTO containing data to create a new EDD record
     * @return A Mono containing the created EnhancedDueDiligenceDTO
     */
    Mono<EnhancedDueDiligenceDTO> create(EnhancedDueDiligenceDTO dto);

    /**
     * Retrieves an EnhancedDueDiligenceDTO by its unique identifier.
     *
     * @param eddId The ID of the EnhancedDueDiligence to retrieve.
     * @return A Mono containing the EnhancedDueDiligenceDTO if found, otherwise an empty mono.
     */
    Mono<EnhancedDueDiligenceDTO> getById(Long eddId);

    /**
     * Updates an existing EnhancedDueDiligence entry with new data provided in the DTO.
     *
     * @param eddId The ID of the EnhancedDueDiligence to be updated.
     * @param dto A DTO containing the fields to update for the EnhancedDueDiligence.
     * @return A Mono containing the updated EnhancedDueDiligenceDTO if successful.
     */
    Mono<EnhancedDueDiligenceDTO> update(Long eddId, EnhancedDueDiligenceDTO dto);

    /**
     * Deletes an Enhanced Due Diligence record by its ID.
     *
     * @param eddId The ID of the Enhanced Due Diligence record to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long eddId);
}
