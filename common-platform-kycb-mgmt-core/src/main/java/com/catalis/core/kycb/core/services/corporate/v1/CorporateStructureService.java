package com.catalis.core.kycb.core.services.corporate.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.corporate.v1.CorporateStructureDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for corporate structure operations.
 */
public interface CorporateStructureService {
    /**
     * Retrieves all corporate structure relationships based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving corporate structure relationships.
     * @return A {@link Mono} containing a paginated response of corporate structure DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<CorporateStructureDTO>> findAll(FilterRequest<CorporateStructureDTO> filterRequest);

    /**
     * Creates a new corporate structure relationship based on the provided data transfer object.
     *
     * @param dto The CorporateStructureDTO containing data to create a new corporate structure record
     * @return A Mono containing the created CorporateStructureDTO
     */
    Mono<CorporateStructureDTO> create(CorporateStructureDTO dto);

    /**
     * Retrieves a CorporateStructureDTO by its unique identifier.
     *
     * @param structureId The ID of the CorporateStructure to retrieve.
     * @return A Mono containing the CorporateStructureDTO if found, otherwise an empty mono.
     */
    Mono<CorporateStructureDTO> getById(Long structureId);

    /**
     * Updates an existing CorporateStructure entry with new data provided in the DTO.
     *
     * @param structureId The ID of the CorporateStructure to be updated.
     * @param dto A DTO containing the fields to update for the CorporateStructure.
     * @return A Mono containing the updated CorporateStructureDTO if successful.
     */
    Mono<CorporateStructureDTO> update(Long structureId, CorporateStructureDTO dto);

    /**
     * Deletes a Corporate Structure relationship by its ID.
     *
     * @param structureId The ID of the Corporate Structure relationship to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long structureId);
}
