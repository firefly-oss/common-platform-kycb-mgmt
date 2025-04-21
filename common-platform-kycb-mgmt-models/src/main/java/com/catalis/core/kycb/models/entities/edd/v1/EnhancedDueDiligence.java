package com.catalis.core.kycb.models.entities.edd.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddReasonEnum;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing an enhanced due diligence process.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("enhanced_due_diligence")
public class EnhancedDueDiligence extends BaseEntity {

    @Id
    @Column("edd_id")
    private Long eddId;

    @Column("kyc_verification_id")
    private Long kycVerificationId;

    @Column("edd_reason")
    private EddReasonEnum eddReason;

    @Column("edd_status")
    private EddStatusEnum eddStatus;

    @Column("edd_description")
    private String eddDescription;

    @Column("approving_authority")
    private String approvingAuthority;

    @Column("approval_date")
    private LocalDateTime approvalDate;

    @Column("edd_notes")
    private String eddNotes;

    @Column("internal_committee_approval")
    private Boolean internalCommitteeApproval;

    @Column("committee_approval_date")
    private LocalDateTime committeeApprovalDate;

    @Column("completion_date")
    private LocalDateTime completionDate;

    @Column("completed_by")
    private String completedBy;
}