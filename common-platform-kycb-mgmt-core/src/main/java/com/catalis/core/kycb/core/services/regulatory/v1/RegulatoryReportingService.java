package com.catalis.core.kycb.core.services.regulatory.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Service interface for regulatory reporting operations.
 */
public interface RegulatoryReportingService {
    /**
     * Retrieves all regulatory reports based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving regulatory reports.
     * @return A {@link Mono} containing a paginated response of regulatory report DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<RegulatoryReportingDTO>> findAll(FilterRequest<RegulatoryReportingDTO> filterRequest);
    
    /**
     * Retrieves all regulatory reports for a specific compliance case.
     *
     * @param caseId The ID of the compliance case.
     * @return A {@link Flux} of regulatory report DTOs.
     */
    Flux<RegulatoryReportingDTO> findByComplianceCaseId(Long caseId);
    
    /**
     * Creates a new regulatory report entry based on the provided data transfer object.
     *
     * @param dto The RegulatoryReportingDTO containing data to create a new regulatory report record
     * @return A Mono containing the created RegulatoryReportingDTO
     */
    Mono<RegulatoryReportingDTO> create(RegulatoryReportingDTO dto);
    
    /**
     * Retrieves a RegulatoryReportingDTO by its unique identifier.
     *
     * @param reportId The ID of the RegulatoryReporting to retrieve.
     * @return A Mono containing the RegulatoryReportingDTO if found, otherwise an empty mono.
     */
    Mono<RegulatoryReportingDTO> getById(Long reportId);
    
    /**
     * Updates an existing RegulatoryReporting entry with new data provided in the DTO.
     *
     * @param reportId The ID of the RegulatoryReporting to be updated.
     * @param dto A DTO containing the fields to update for the RegulatoryReporting.
     * @return A Mono containing the updated RegulatoryReportingDTO if successful.
     */
    Mono<RegulatoryReportingDTO> update(Long reportId, RegulatoryReportingDTO dto);
    
    /**
     * Deletes a Regulatory Report by its ID.
     *
     * @param reportId The ID of the Regulatory Report to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long reportId);
    
    /**
     * Retrieves all regulatory reports with a specific report type.
     *
     * @param reportType The report type.
     * @return A {@link Flux} of regulatory report DTOs.
     */
    Flux<RegulatoryReportingDTO> findByReportType(String reportType);
    
    /**
     * Retrieves all regulatory reports with a specific report status.
     *
     * @param reportStatus The report status.
     * @return A {@link Flux} of regulatory report DTOs.
     */
    Flux<RegulatoryReportingDTO> findByReportStatus(String reportStatus);
    
    /**
     * Retrieves all regulatory reports submitted by a specific user.
     *
     * @param submittedBy The user who submitted the report.
     * @return A {@link Flux} of regulatory report DTOs.
     */
    Flux<RegulatoryReportingDTO> findBySubmittedBy(String submittedBy);
    
    /**
     * Retrieves all regulatory reports submitted between the specified date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return A {@link Flux} of regulatory report DTOs.
     */
    Flux<RegulatoryReportingDTO> findBySubmissionDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    
    /**
     * Retrieves all regulatory reports for a specific compliance case with a specific report status.
     *
     * @param caseId The ID of the compliance case.
     * @param reportStatus The report status.
     * @return A {@link Flux} of regulatory report DTOs.
     */
    Flux<RegulatoryReportingDTO> findByComplianceCaseIdAndReportStatus(Long caseId, String reportStatus);
    
    /**
     * Retrieves the latest regulatory report for a compliance case.
     *
     * @param caseId The ID of the compliance case.
     * @return A Mono containing the latest RegulatoryReportingDTO.
     */
    Mono<RegulatoryReportingDTO> getLatestByComplianceCaseId(Long caseId);
    
    /**
     * Submits a regulatory report.
     *
     * @param reportId The ID of the report to submit.
     * @param submittedBy The user submitting the report.
     * @param submissionNotes Notes about the submission.
     * @return A Mono containing the updated RegulatoryReportingDTO.
     */
    Mono<RegulatoryReportingDTO> submitReport(Long reportId, String submittedBy, String submissionNotes);
    
    /**
     * Approves a regulatory report.
     *
     * @param reportId The ID of the report to approve.
     * @param approvedBy The user approving the report.
     * @param approvalNotes Notes about the approval.
     * @return A Mono containing the updated RegulatoryReportingDTO.
     */
    Mono<RegulatoryReportingDTO> approveReport(Long reportId, String approvedBy, String approvalNotes);
    
    /**
     * Rejects a regulatory report.
     *
     * @param reportId The ID of the report to reject.
     * @param rejectedBy The user rejecting the report.
     * @param rejectionReason The reason for rejection.
     * @return A Mono containing the updated RegulatoryReportingDTO.
     */
    Mono<RegulatoryReportingDTO> rejectReport(Long reportId, String rejectedBy, String rejectionReason);
}