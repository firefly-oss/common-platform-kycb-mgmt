package com.catalis.core.kycb.models.entities.compliance.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.compliance.v1.CasePriorityEnum;
import com.catalis.core.kycb.interfaces.enums.compliance.v1.CaseStatusEnum;
import com.catalis.core.kycb.interfaces.enums.compliance.v1.CaseTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a compliance case.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("compliance_case")
public class ComplianceCase extends BaseEntity {

    @Id
    @Column("compliance_case_id")
    private Long complianceCaseId;

    @Column("party_id")
    private Long partyId;

    @Column("case_type")
    private CaseTypeEnum caseType;

    @Column("case_status")
    private CaseStatusEnum caseStatus;

    @Column("case_priority")
    private CasePriorityEnum casePriority;

    @Column("case_reference")
    private String caseReference;

    @Column("case_summary")
    private String caseSummary;

    @Column("assigned_to")
    private String assignedTo;

    @Column("due_date")
    private LocalDateTime dueDate;

    @Column("resolution_date")
    private LocalDateTime resolutionDate;

    @Column("resolution_notes")
    private String resolutionNotes;

    @Column("report_to_sepblac_required")
    private Boolean reportToSepblacRequired;
}