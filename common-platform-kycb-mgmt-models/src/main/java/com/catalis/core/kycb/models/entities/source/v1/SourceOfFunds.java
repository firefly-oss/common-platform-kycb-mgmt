package com.catalis.core.kycb.models.entities.source.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.source.v1.SourceTypeEnum;
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
 * Entity representing a source of funds declaration.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("source_of_funds")
public class SourceOfFunds extends BaseEntity {

    @Id
    @Column("source_of_funds_id")
    private Long sourceOfFundsId;

    @Column("party_id")
    private Long partyId;

    @Column("source_type")
    private SourceTypeEnum sourceType;

    @Column("source_description")
    private String sourceDescription;

    @Column("estimated_annual_amount")
    private BigDecimal estimatedAnnualAmount;

    @Column("currency")
    private String currency;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verification_method")
    private String verificationMethod;

    @Column("supporting_documents")
    private String supportingDocuments;

    @Column("verification_date")
    private LocalDateTime verificationDate;
}