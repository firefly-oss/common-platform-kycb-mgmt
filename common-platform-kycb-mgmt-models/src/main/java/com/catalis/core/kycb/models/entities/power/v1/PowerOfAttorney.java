package com.catalis.core.kycb.models.entities.power.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.power.v1.PowerTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a power of attorney.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("power_of_attorney")
public class PowerOfAttorney extends BaseEntity {

    @Id
    @Column("power_of_attorney_id")
    private Long powerOfAttorneyId;

    @Column("corporate_document_id")
    private Long corporateDocumentId;

    @Column("party_id")
    private Long partyId;

    @Column("attorney_id")
    private Long attorneyId;

    @Column("power_type")
    private PowerTypeEnum powerType;

    @Column("power_scope")
    private String powerScope;

    @Column("joint_signature_required")
    private Boolean jointSignatureRequired;

    @Column("joint_signature_count")
    private Integer jointSignatureCount;

    @Column("joint_signature_notes")
    private String jointSignatureNotes;

    @Column("financial_limit")
    private BigDecimal financialLimit;

    @Column("currency")
    private String currency;

    @Column("effective_date")
    private LocalDateTime effectiveDate;

    @Column("expiry_date")
    private LocalDateTime expiryDate;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("is_bastanteo_completed")
    private Boolean isBastanteoCompleted;

    @Column("verification_method")
    private String verificationMethod;

    @Column("verification_date")
    private LocalDateTime verificationDate;

    @Column("verifying_legal_counsel")
    private String verifyingLegalCounsel;
}
