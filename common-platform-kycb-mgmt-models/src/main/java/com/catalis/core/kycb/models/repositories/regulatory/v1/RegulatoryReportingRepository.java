package com.catalis.core.kycb.models.repositories.regulatory.v1;

import com.catalis.core.kycb.models.entities.regulatory.v1.RegulatoryReporting;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportStatusEnum;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for regulatory reporting operations.
 */
@Repository
public interface RegulatoryReportingRepository extends BaseRepository<RegulatoryReporting, Long> {

    /**
     * Find regulatory reports by compliance case ID.
     *
     * @param complianceCaseId The ID of the compliance case
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByComplianceCaseId(Long complianceCaseId);

    /**
     * Find regulatory reports by report type.
     *
     * @param reportTypeEnum The type of report
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByReportType(ReportTypeEnum reportTypeEnum);

    /**
     * Find regulatory reports by report status.
     *
     * @param reportStatusEnum The status of the report
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByReportStatus(ReportStatusEnum reportStatusEnum);

    /**
     * Find regulatory reports by regulatory authority.
     *
     * @param regulatoryAuthority The regulatory authority
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByRegulatoryAuthority(String regulatoryAuthority);

    /**
     * Find regulatory reports by submitting agent.
     *
     * @param submittingAgent The agent who submitted the report
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findBySubmittingAgent(String submittingAgent);

    /**
     * Find regulatory reports by submission date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findBySubmissionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find regulatory reports by acknowledgment date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByAcknowledgmentDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find regulatory reports by report reference.
     *
     * @param reportReference The report reference
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByReportReference(String reportReference);

    /**
     * Find regulatory reports by compliance case ID and report status.
     *
     * @param complianceCaseId The ID of the compliance case
     * @param reportStatusEnum The status of the report
     * @return A flux of regulatory reports
     */
    Flux<RegulatoryReporting> findByComplianceCaseIdAndReportStatus(Long complianceCaseId, ReportStatusEnum reportStatusEnum);

    /**
     * Find the latest regulatory report for a compliance case.
     *
     * @param complianceCaseId The ID of the compliance case
     * @return A mono with the latest regulatory report
     */
    Mono<RegulatoryReporting> findFirstByComplianceCaseIdOrderBySubmissionDateDesc(Long complianceCaseId);
}