package com.catalis.core.kycb.models.entities.kyc.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.interfaces.enums.verification.v1.VerificationMethodEnum;
import com.catalis.core.kycb.interfaces.enums.verification.v1.VerificationStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a KYC verification record.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("kyc_verification")
public class KycVerification extends BaseEntity {

    @Id
    @Column("kyc_verification_id")
    private Long kycVerificationId;

    @Column("party_id")
    private Long partyId;

    @Column("verification_status")
    private VerificationStatusEnum verificationStatus;

    @Column("verification_date")
    private LocalDateTime verificationDate;

    @Column("verification_method")
    private VerificationMethodEnum verificationMethod;

    @Column("verification_agent")
    private String verificationAgent;

    @Column("rejection_reason")
    private String rejectionReason;

    @Column("risk_score")
    private Integer riskScore;

    @Column("risk_level")
    private RiskLevelEnum riskLevel;

    @Column("enhanced_due_diligence")
    private Boolean enhancedDueDiligence;

    @Column("next_review_date")
    private LocalDateTime nextReviewDate;
}