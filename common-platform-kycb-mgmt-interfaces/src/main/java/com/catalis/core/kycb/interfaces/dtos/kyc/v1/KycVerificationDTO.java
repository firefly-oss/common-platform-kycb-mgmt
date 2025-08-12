package com.catalis.core.kycb.interfaces.dtos.kyc.v1;

import com.catalis.annotations.ValidDateTime;
import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for KYC verification data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KycVerificationDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long kycVerificationId;

    @FilterableId
    private Long partyId;

    private String verificationStatus;
    @ValidDateTime
    private LocalDateTime verificationDate;
    private String verificationMethod;
    private String verificationAgent;
    private String rejectionReason;
    private Integer riskScore;
    private String riskLevel;
    private Boolean enhancedDueDiligence;
    @ValidDateTime
    private LocalDateTime nextReviewDate;
}
