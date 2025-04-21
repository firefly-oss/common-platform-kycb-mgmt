package com.catalis.core.kycb.core.services.kyc.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.kyc.v1.KycVerificationDTO;
import reactor.core.publisher.Mono;

public interface KycVerificationService {
    /**
     * Retrieves all KYC verification records based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving KYC verifications.
     * @return A {@link Mono} containing a paginated response of KYC verification DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<KycVerificationDTO>> findAll(FilterRequest<KycVerificationDTO> filterRequest);
    /**
     * Creates a new KYC verification entry based on the provided data transfer object.
     *
     * @param dto The KycVerificationDTO containing data to create a new KYC verification record
     * @return A Mono containing the created KycVerificationDTO
     */
    Mono<KycVerificationDTO> create(KycVerificationDTO dto);
    /**
     * Retrieves a KycVerificationDTO by its unique identifier.
     *
     * @param kycVerificationId The ID of the KycVerification to retrieve.
     * @return A Mono containing the KycVerificationDTO if found, otherwise an empty mono.
     */
    Mono<KycVerificationDTO> getById(Long kycVerificationId);
    /**
     * Updates an existing KycVerification entry with new data provided in the DTO.
     *
     * @param kycVerificationId The ID of the KycVerification to be updated.
     * @param dto A DTO containing the fields to update for the KycVerification.
     * @return A Mono containing the updated KycVerificationDTO if successful.
     */
    Mono<KycVerificationDTO> update(Long kycVerificationId, KycVerificationDTO dto);
    /**
     * Deletes a KYC Verification by its ID.
     *
     * @param kycVerificationId The ID of the KYC Verification to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long kycVerificationId);
    /**
     * Completes the KYC verification process for the specified verification ID.
     *
     * @param kycVerificationId The ID of the KYC verification to complete.
     * @return A Mono containing the updated KycVerificationDTO after completion.
     */
    Mono<KycVerificationDTO> complete(Long kycVerificationId);

}