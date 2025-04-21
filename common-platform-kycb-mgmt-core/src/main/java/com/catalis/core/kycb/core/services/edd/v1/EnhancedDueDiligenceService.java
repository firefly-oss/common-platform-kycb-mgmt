package com.catalis.core.kycb.core.services.edd.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.edd.v1.EnhancedDueDiligenceDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Service interface for enhanced due diligence operations.
 */
public interface EnhancedDueDiligenceService {
    /**
     * Retrieves all enhanced due diligence records based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving EDD records.
     * @return A {@link Mono} containing a paginated response of EDD DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<EnhancedDueDiligenceDTO>> findAll(FilterRequest<EnhancedDueDiligenceDTO> filterRequest);
    
    /**
     * Retrieves all enhanced due diligence records for a specific KYC verification.
     *
     * @param kycVerificationId The ID of the KYC verification.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByKycVerificationId(Long kycVerificationId);
    
    /**
     * Creates a new enhanced due diligence entry based on the provided data transfer object.
     *
     * @param dto The EnhancedDueDiligenceDTO containing data to create a new EDD record
     * @return A Mono containing the created EnhancedDueDiligenceDTO
     */
    Mono<EnhancedDueDiligenceDTO> create(EnhancedDueDiligenceDTO dto);
    
    /**
     * Retrieves an EnhancedDueDiligenceDTO by its unique identifier.
     *
     * @param eddId The ID of the EnhancedDueDiligence to retrieve.
     * @return A Mono containing the EnhancedDueDiligenceDTO if found, otherwise an empty mono.
     */
    Mono<EnhancedDueDiligenceDTO> getById(Long eddId);
    
    /**
     * Updates an existing EnhancedDueDiligence entry with new data provided in the DTO.
     *
     * @param eddId The ID of the EnhancedDueDiligence to be updated.
     * @param dto A DTO containing the fields to update for the EnhancedDueDiligence.
     * @return A Mono containing the updated EnhancedDueDiligenceDTO if successful.
     */
    Mono<EnhancedDueDiligenceDTO> update(Long eddId, EnhancedDueDiligenceDTO dto);
    
    /**
     * Deletes an Enhanced Due Diligence record by its ID.
     *
     * @param eddId The ID of the Enhanced Due Diligence record to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long eddId);
    
    /**
     * Retrieves all enhanced due diligence records with a specific reason.
     *
     * @param eddReason The reason for the EDD.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByEddReason(String eddReason);
    
    /**
     * Retrieves all enhanced due diligence records with a specific status.
     *
     * @param eddStatus The status of the EDD.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByEddStatus(String eddStatus);
    
    /**
     * Retrieves all enhanced due diligence records approved by a specific authority.
     *
     * @param approvingAuthority The approving authority.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByApprovingAuthority(String approvingAuthority);
    
    /**
     * Retrieves all enhanced due diligence records with a specific internal committee approval status.
     *
     * @param internalCommitteeApproval The internal committee approval status.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByInternalCommitteeApproval(Boolean internalCommitteeApproval);
    
    /**
     * Retrieves all enhanced due diligence records approved between the specified date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByApprovalDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Retrieves all enhanced due diligence records completed between the specified date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByCompletionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Retrieves all enhanced due diligence records completed by a specific user.
     *
     * @param completedBy The user who completed the EDD.
     * @return A {@link Flux} of EDD DTOs.
     */
    Flux<EnhancedDueDiligenceDTO> findByCompletedBy(String completedBy);
    
    /**
     * Retrieves the latest enhanced due diligence record for a KYC verification.
     *
     * @param kycVerificationId The ID of the KYC verification.
     * @return A Mono containing the latest EnhancedDueDiligenceDTO.
     */
    Mono<EnhancedDueDiligenceDTO> getLatestByKycVerificationId(Long kycVerificationId);
    
    /**
     * Completes an enhanced due diligence process.
     *
     * @param eddId The ID of the EDD to complete.
     * @param completedBy The user completing the EDD.
     * @param completionNotes Notes about the completion.
     * @return A Mono containing the updated EnhancedDueDiligenceDTO.
     */
    Mono<EnhancedDueDiligenceDTO> completeEdd(Long eddId, String completedBy, String completionNotes);
    
    /**
     * Approves an enhanced due diligence process.
     *
     * @param eddId The ID of the EDD to approve.
     * @param approvingAuthority The authority approving the EDD.
     * @param approvalNotes Notes about the approval.
     * @return A Mono containing the updated EnhancedDueDiligenceDTO.
     */
    Mono<EnhancedDueDiligenceDTO> approveEdd(Long eddId, String approvingAuthority, String approvalNotes);
    
    /**
     * Waives an enhanced due diligence process.
     *
     * @param eddId The ID of the EDD to waive.
     * @param waiveReason The reason for waiving the EDD.
     * @param waiveAuthority The authority waiving the EDD.
     * @return A Mono containing the updated EnhancedDueDiligenceDTO.
     */
    Mono<EnhancedDueDiligenceDTO> waiveEdd(Long eddId, String waiveReason, String waiveAuthority);
}