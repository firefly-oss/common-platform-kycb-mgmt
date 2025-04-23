package com.catalis.core.kycb.core.services.power.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.power.v1.PowerOfAttorneyDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for power of attorney operations.
 */
public interface PowerOfAttorneyService {
    /**
     * Retrieves all powers of attorney based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving powers of attorney.
     * @return A {@link Mono} containing a paginated response of power of attorney DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<PowerOfAttorneyDTO>> findAll(FilterRequest<PowerOfAttorneyDTO> filterRequest);

    /**
     * Creates a new power of attorney entry based on the provided data transfer object.
     *
     * @param dto The PowerOfAttorneyDTO containing data to create a new power of attorney record
     * @return A Mono containing the created PowerOfAttorneyDTO
     */
    Mono<PowerOfAttorneyDTO> create(PowerOfAttorneyDTO dto);

    /**
     * Retrieves a PowerOfAttorneyDTO by its unique identifier.
     *
     * @param powerId The ID of the PowerOfAttorney to retrieve.
     * @return A Mono containing the PowerOfAttorneyDTO if found, otherwise an empty mono.
     */
    Mono<PowerOfAttorneyDTO> getById(Long powerId);

    /**
     * Updates an existing PowerOfAttorney entry with new data provided in the DTO.
     *
     * @param powerId The ID of the PowerOfAttorney to be updated.
     * @param dto A DTO containing the fields to update for the PowerOfAttorney.
     * @return A Mono containing the updated PowerOfAttorneyDTO if successful.
     */
    Mono<PowerOfAttorneyDTO> update(Long powerId, PowerOfAttorneyDTO dto);

    /**
     * Deletes a Power of Attorney by its ID.
     *
     * @param powerId The ID of the Power of Attorney to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long powerId);
}
