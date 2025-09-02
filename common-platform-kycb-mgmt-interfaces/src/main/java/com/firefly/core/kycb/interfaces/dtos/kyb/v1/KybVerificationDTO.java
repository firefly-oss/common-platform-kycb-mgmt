package com.firefly.core.kycb.interfaces.dtos.kyb.v1;

import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID kybVerificationId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotBlank(message = "Verification status is required")
    @Size(max = 50, message = "Verification status must not exceed 50 characters")
    private String verificationStatus;

    @NotNull(message = "Verification date is required")
    @ValidDateTime
    private LocalDateTime verificationDate;

    @NotNull(message = "Mercantile registry verified flag is required")
    private Boolean mercantileRegistryVerified;

    @NotNull(message = "Deed of incorporation verified flag is required")
    private Boolean deedOfIncorporationVerified;

    @NotNull(message = "Business structure verified flag is required")
    private Boolean businessStructureVerified;

    @NotNull(message = "UBO verified flag is required")
    private Boolean uboVerified;

    @NotNull(message = "Tax ID verified flag is required")
    private Boolean taxIdVerified;

    @NotNull(message = "Operating license verified flag is required")
    private Boolean operatingLicenseVerified;

    @Size(max = 1000, message = "Verification notes must not exceed 1000 characters")
    private String verificationNotes;

    @NotNull(message = "Risk score is required")
    @Min(value = 0, message = "Risk score must be between 0 and 100")
    @Max(value = 100, message = "Risk score must be between 0 and 100")
    private Integer riskScore;

    @NotBlank(message = "Risk level is required")
    @Size(max = 20, message = "Risk level must not exceed 20 characters")
    private String riskLevel;

    @ValidDateTime
    private LocalDateTime nextReviewDate;
}
