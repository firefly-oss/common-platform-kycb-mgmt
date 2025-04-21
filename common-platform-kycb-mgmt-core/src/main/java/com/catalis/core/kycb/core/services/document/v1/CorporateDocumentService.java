package com.catalis.core.kycb.core.services.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.document.v1.CorporateDocumentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for corporate document operations.
 */
public interface CorporateDocumentService {
    /**
     * Retrieves all corporate documents based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving corporate documents.
     * @return A {@link Mono} containing a paginated response of corporate document DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<CorporateDocumentDTO>> findAll(FilterRequest<CorporateDocumentDTO> filterRequest);

    /**
     * Retrieves all corporate documents for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of corporate document DTOs.
     */
    Flux<CorporateDocumentDTO> findByPartyId(Long partyId);

    /**
     * Creates a new corporate document entry based on the provided data transfer object.
     *
     * @param dto The CorporateDocumentDTO containing data to create a new corporate document record
     * @return A Mono containing the created CorporateDocumentDTO
     */
    Mono<CorporateDocumentDTO> create(CorporateDocumentDTO dto);

    /**
     * Retrieves a CorporateDocumentDTO by its unique identifier.
     *
     * @param documentId The ID of the CorporateDocument to retrieve.
     * @return A Mono containing the CorporateDocumentDTO if found, otherwise an empty mono.
     */
    Mono<CorporateDocumentDTO> getById(Long documentId);

    /**
     * Updates an existing CorporateDocument entry with new data provided in the DTO.
     *
     * @param documentId The ID of the CorporateDocument to be updated.
     * @param dto A DTO containing the fields to update for the CorporateDocument.
     * @return A Mono containing the updated CorporateDocumentDTO if successful.
     */
    Mono<CorporateDocumentDTO> update(Long documentId, CorporateDocumentDTO dto);

    /**
     * Deletes a Corporate Document by its ID.
     *
     * @param documentId The ID of the Corporate Document to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long documentId);

    /**
     * Retrieves all corporate documents of a specific type.
     *
     * @param documentType The type of the corporate documents to retrieve.
     * @return A {@link Flux} of corporate document DTOs.
     */
    Flux<CorporateDocumentDTO> findByDocumentType(String documentType);

    /**
     * Verifies a corporate document.
     *
     * @param documentId The ID of the corporate document to verify.
     * @param verificationNotes Notes explaining the verification.
     * @return A Mono containing the updated CorporateDocumentDTO.
     */
    Mono<CorporateDocumentDTO> verifyDocument(Long documentId, String verificationNotes);
}
