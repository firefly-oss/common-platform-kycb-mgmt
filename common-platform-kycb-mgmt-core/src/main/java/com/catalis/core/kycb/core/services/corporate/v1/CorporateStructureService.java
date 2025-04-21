package com.catalis.core.kycb.core.services.corporate.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.corporate.v1.CorporateStructureDTO;
import reactor.core.publisher.Flux;
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
     * Retrieves all corporate structure relationships for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of corporate structure DTOs.
     */
    Flux<CorporateStructureDTO> findByPartyId(Long partyId);

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

    /**
     * Retrieves all corporate structure relationships of a specific type.
     *
     * @param relationshipType The type of the corporate structure relationships to retrieve.
     * @return A {@link Flux} of corporate structure DTOs.
     */
    Flux<CorporateStructureDTO> findByRelationshipType(String relationshipType);

    /**
     * Retrieves all corporate structure relationships where the specified party is the parent.
     *
     * @param parentId The ID of the parent party.
     * @return A {@link Flux} of corporate structure DTOs.
     */
    Flux<CorporateStructureDTO> findByParentId(Long parentId);

    /**
     * Retrieves all corporate structure relationships where the specified party is the subsidiary.
     *
     * @param subsidiaryId The ID of the subsidiary party.
     * @return A {@link Flux} of corporate structure DTOs.
     */
    Flux<CorporateStructureDTO> findBySubsidiaryId(Long subsidiaryId);

    /**
     * Verifies a corporate structure relationship.
     *
     * @param structureId The ID of the corporate structure relationship to verify.
     * @param verificationNotes Notes explaining the verification.
     * @return A Mono containing the updated CorporateStructureDTO.
     */
    Mono<CorporateStructureDTO> verifyStructure(Long structureId, String verificationNotes);
}
