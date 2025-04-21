package com.catalis.core.kycb.interfaces.dtos.kyb.v1;

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
 * DTO for KYB verification data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class KybVerificationDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long kybVerificationId;

    @FilterableId
    private Long partyId;

    private String verificationStatus;
    private LocalDateTime verificationDate;
    private Boolean mercantileRegistryVerified;
    private Boolean deedOfIncorporationVerified;
    private Boolean businessStructureVerified;
    private Boolean uboVerified;
    private Boolean taxIdVerified;
    private Boolean operatingLicenseVerified;
    private String verificationNotes;
    private Integer riskScore;
    private String riskLevel;
    private LocalDateTime nextReviewDate;
}
