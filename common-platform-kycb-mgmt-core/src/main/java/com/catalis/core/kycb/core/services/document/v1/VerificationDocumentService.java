package com.catalis.core.kycb.core.services.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.document.v1.VerificationDocumentDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface VerificationDocumentService {
    /**
     * Retrieves all verification documents based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving verification documents.
     * @return A {@link Mono} containing a paginated response of verification document DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<VerificationDocumentDTO>> findAll(FilterRequest<VerificationDocumentDTO> filterRequest);
    
    /**
     * Retrieves all verification documents for a specific KYC verification.
     *
     * @param kycVerificationId The ID of the KYC verification.
     * @return A {@link Flux} of verification document DTOs.
     */
    Flux<VerificationDocumentDTO> findByKycVerificationId(Long kycVerificationId);
    
    /**
     * Creates a new verification document entry based on the provided data transfer object.
     *
     * @param dto The VerificationDocumentDTO containing data to create a new verification document record
     * @return A Mono containing the created VerificationDocumentDTO
     */
    Mono<VerificationDocumentDTO> create(VerificationDocumentDTO dto);
    
    /**
     * Retrieves a VerificationDocumentDTO by its unique identifier.
     *
     * @param verificationDocumentId The ID of the VerificationDocument to retrieve.
     * @return A Mono containing the VerificationDocumentDTO if found, otherwise an empty mono.
     */
    Mono<VerificationDocumentDTO> getById(Long verificationDocumentId);
    
    /**
     * Updates an existing VerificationDocument entry with new data provided in the DTO.
     *
     * @param verificationDocumentId The ID of the VerificationDocument to be updated.
     * @param dto A DTO containing the fields to update for the VerificationDocument.
     * @return A Mono containing the updated VerificationDocumentDTO if successful.
     */
    Mono<VerificationDocumentDTO> update(Long verificationDocumentId, VerificationDocumentDTO dto);
    
    /**
     * Deletes a Verification Document by its ID.
     *
     * @param verificationDocumentId The ID of the Verification Document to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long verificationDocumentId);
    
    /**
     * Marks a verification document as verified.
     *
     * @param verificationDocumentId The ID of the verification document to mark as verified.
     * @return A Mono containing the updated VerificationDocumentDTO after verification.
     */
    Mono<VerificationDocumentDTO> verify(Long verificationDocumentId);
}