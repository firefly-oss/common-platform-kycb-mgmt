package com.catalis.core.kycb.models.entities.regulatory.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportStatusEnum;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a regulatory report.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("regulatory_reporting")
public class RegulatoryReporting extends BaseEntity {

    @Id
    @Column("report_id")
    private Long reportId;

    @Column("compliance_case_id")
    private Long complianceCaseId;

    @Column("report_type")
    private ReportTypeEnum reportType;

    @Column("report_reference")
    private String reportReference;

    @Column("regulatory_authority")
    private String regulatoryAuthority;

    @Column("report_status")
    private ReportStatusEnum reportStatus;

    @Column("submission_date")
    private LocalDateTime submissionDate;

    @Column("submitting_agent")
    private String submittingAgent;

    @Column("acknowledgment_date")
    private LocalDateTime acknowledgmentDate;

    @Column("report_content_summary")
    private String reportContentSummary;
}