package com.catalis.core.kycb.models.entities.kyb.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
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
 * Entity representing a KYB (Know Your Business) verification record.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("kyb_verification")
public class KybVerification extends BaseEntity {

    @Id
    @Column("kyb_verification_id")
    private Long kybVerificationId;

    @Column("party_id")
    private Long partyId;

    @Column("verification_status")
    private VerificationStatusEnum verificationStatus;

    @Column("verification_date")
    private LocalDateTime verificationDate;

    @Column("mercantile_registry_verified")
    private Boolean mercantileRegistryVerified;

    @Column("deed_of_incorporation_verified")
    private Boolean deedOfIncorporationVerified;

    @Column("business_structure_verified")
    private Boolean businessStructureVerified;

    @Column("ubo_verified")
    private Boolean uboVerified;

    @Column("tax_id_verified")
    private Boolean taxIdVerified;

    @Column("operating_license_verified")
    private Boolean operatingLicenseVerified;

    @Column("verification_notes")
    private String verificationNotes;

    @Column("risk_score")
    private Integer riskScore;

    @Column("risk_level")
    private RiskLevelEnum riskLevel;

    @Column("next_review_date")
    private LocalDateTime nextReviewDate;
}
