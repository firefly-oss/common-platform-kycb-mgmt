package com.firefly.core.kycb.core.services.location.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.interfaces.dtos.location.v1.BusinessLocationDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for business location operations.
 */
public interface BusinessLocationService {
    /**
     * Retrieves all business locations based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving business locations.
     * @return A {@link Mono} containing a paginated response of business location DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<BusinessLocationDTO>> findAll(FilterRequest<BusinessLocationDTO> filterRequest);

    /**
     * Creates a new business location entry based on the provided data transfer object.
     *
     * @param dto The BusinessLocationDTO containing data to create a new business location record
     * @return A Mono containing the created BusinessLocationDTO
     */
    Mono<BusinessLocationDTO> create(BusinessLocationDTO dto);

    /**
     * Retrieves a BusinessLocationDTO by its unique identifier.
     *
     * @param locationId The ID of the BusinessLocation to retrieve.
     * @return A Mono containing the BusinessLocationDTO if found, otherwise an empty mono.
     */
    Mono<BusinessLocationDTO> getById(UUID locationId);

    /**
     * Updates an existing BusinessLocation entry with new data provided in the DTO.
     *
     * @param locationId The ID of the BusinessLocation to be updated.
     * @param dto A DTO containing the fields to update for the BusinessLocation.
     * @return A Mono containing the updated BusinessLocationDTO if successful.
     */
    Mono<BusinessLocationDTO> update(UUID locationId, BusinessLocationDTO dto);

    /**
     * Deletes a Business Location by its ID.
     *
     * @param locationId The ID of the Business Location to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(UUID locationId);
}
