package com.catalis.core.kycb.core.services.kyb.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.kyb.v1.KybVerificationDTO;
import reactor.core.publisher.Mono;

public interface KybVerificationService {
    /**
     * Retrieves all KYB verification records based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving KYB verifications.
     * @return A {@link Mono} containing a paginated response of KYB verification DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<KybVerificationDTO>> findAll(FilterRequest<KybVerificationDTO> filterRequest);
    
    /**
     * Creates a new KYB verification entry based on the provided data transfer object.
     *
     * @param dto The KybVerificationDTO containing data to create a new KYB verification record
     * @return A Mono containing the created KybVerificationDTO
     */
    Mono<KybVerificationDTO> create(KybVerificationDTO dto);
    
    /**
     * Retrieves a KybVerificationDTO by its unique identifier.
     *
     * @param kybVerificationId The ID of the KybVerification to retrieve.
     * @return A Mono containing the KybVerificationDTO if found, otherwise an empty mono.
     */
    Mono<KybVerificationDTO> getById(Long kybVerificationId);
    
    /**
     * Updates an existing KybVerification entry with new data provided in the DTO.
     *
     * @param kybVerificationId The ID of the KybVerification to be updated.
     * @param dto A DTO containing the fields to update for the KybVerification.
     * @return A Mono containing the updated KybVerificationDTO if successful.
     */
    Mono<KybVerificationDTO> update(Long kybVerificationId, KybVerificationDTO dto);
    
    /**
     * Deletes a KYB Verification by its ID.
     *
     * @param kybVerificationId The ID of the KYB Verification to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long kybVerificationId);
    
    /**
     * Completes the KYB verification process for the specified verification ID.
     *
     * @param kybVerificationId The ID of the KYB verification to complete.
     * @return A Mono containing the updated KybVerificationDTO after completion.
     */
    Mono<KybVerificationDTO> complete(Long kybVerificationId);
}