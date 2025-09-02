package com.firefly.core.kycb.core.services.business.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.interfaces.dtos.business.v1.BusinessProfileDTO;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Service interface for business profile operations.
 */
public interface BusinessProfileService {
    /**
     * Retrieves all business profiles based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving business profiles.
     * @return A {@link Mono} containing a paginated response of business profile DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<BusinessProfileDTO>> findAll(FilterRequest<BusinessProfileDTO> filterRequest);

    /**
     * Creates a new business profile entry based on the provided data transfer object.
     *
     * @param dto The BusinessProfileDTO containing data to create a new business profile record
     * @return A Mono containing the created BusinessProfileDTO
     */
    Mono<BusinessProfileDTO> create(BusinessProfileDTO dto);

    /**
     * Retrieves a BusinessProfileDTO by its unique identifier.
     *
     * @param businessProfileId The ID of the BusinessProfile to retrieve.
     * @return A Mono containing the BusinessProfileDTO if found, otherwise an empty mono.
     */
    Mono<BusinessProfileDTO> getById(UUID businessProfileId);

    /**
     * Updates an existing BusinessProfile entry with new data provided in the DTO.
     *
     * @param businessProfileId The ID of the BusinessProfile to be updated.
     * @param dto A DTO containing the fields to update for the BusinessProfile.
     * @return A Mono containing the updated BusinessProfileDTO if successful.
     */
    Mono<BusinessProfileDTO> update(UUID businessProfileId, BusinessProfileDTO dto);

    /**
     * Deletes a Business Profile by its ID.
     *
     * @param businessProfileId The ID of the Business Profile to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(UUID businessProfileId);
}
