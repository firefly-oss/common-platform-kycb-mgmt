package com.catalis.core.kycb.core.services.ownership.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.ownership.v1.UboDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for UBO (Ultimate Beneficial Owner) operations.
 */
public interface UboService {
    /**
     * Retrieves all UBOs based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving UBOs.
     * @return A {@link Mono} containing a paginated response of UBO DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<UboDTO>> findAll(FilterRequest<UboDTO> filterRequest);

    /**
     * Retrieves all UBOs for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of UBO DTOs.
     */
    Flux<UboDTO> findByPartyId(Long partyId);

    /**
     * Creates a new UBO entry based on the provided data transfer object.
     *
     * @param dto The UboDTO containing data to create a new UBO record
     * @return A Mono containing the created UboDTO
     */
    Mono<UboDTO> create(UboDTO dto);

    /**
     * Retrieves a UboDTO by its unique identifier.
     *
     * @param uboId The ID of the UBO to retrieve.
     * @return A Mono containing the UboDTO if found, otherwise an empty mono.
     */
    Mono<UboDTO> getById(Long uboId);

    /**
     * Updates an existing UBO entry with new data provided in the DTO.
     *
     * @param uboId The ID of the UBO to be updated.
     * @param dto A DTO containing the fields to update for the UBO.
     * @return A Mono containing the updated UboDTO if successful.
     */
    Mono<UboDTO> update(Long uboId, UboDTO dto);

    /**
     * Deletes a UBO by its ID.
     *
     * @param uboId The ID of the UBO to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long uboId);

    /**
     * Retrieves all UBOs with ownership percentage above a threshold.
     *
     * @param threshold The ownership percentage threshold.
     * @return A {@link Flux} of UBO DTOs.
     */
    Flux<UboDTO> findByOwnershipPercentageGreaterThan(Double threshold);

    /**
     * Verifies a UBO.
     *
     * @param uboId The ID of the UBO to verify.
     * @param verificationNotes Notes explaining the verification.
     * @return A Mono containing the updated UboDTO.
     */
    Mono<UboDTO> verifyUbo(Long uboId, String verificationNotes);
}
