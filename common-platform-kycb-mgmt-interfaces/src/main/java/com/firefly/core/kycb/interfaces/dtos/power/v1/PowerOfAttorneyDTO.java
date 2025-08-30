package com.firefly.core.kycb.interfaces.dtos.power.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidCurrencyCode;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for power of attorney data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PowerOfAttorneyDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long powerOfAttorneyId;

    @FilterableId
    @NotNull(message = "Corporate document ID is required")
    private Long corporateDocumentId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private Long partyId;

    @FilterableId
    @NotNull(message = "Attorney ID is required")
    private Long attorneyId;

    @NotBlank(message = "Power type is required")
    @Size(max = 50, message = "Power type must not exceed 50 characters")
    private String powerType;

    @NotBlank(message = "Power scope is required")
    @Size(max = 500, message = "Power scope must not exceed 500 characters")
    private String powerScope;

    @NotNull(message = "Joint signature required flag is required")
    private Boolean jointSignatureRequired;

    @Min(value = 1, message = "Joint signature count must be at least 1")
    private Integer jointSignatureCount;

    @Size(max = 1000, message = "Joint signature notes must not exceed 1000 characters")
    private String jointSignatureNotes;

    @ValidAmount
    private BigDecimal financialLimit;

    @ValidCurrencyCode
    private String currency;

    @NotNull(message = "Effective date is required")
    @ValidDateTime
    private LocalDateTime effectiveDate;

    @ValidDateTime
    private LocalDateTime expiryDate;

    @NotNull(message = "Verified status is required")
    private Boolean isVerified;

    @NotNull(message = "POA completed flag is required")
    private Boolean isPoaCompleted;

    @Size(max = 50, message = "Verification method must not exceed 50 characters")
    private String verificationMethod;

    @ValidDateTime
    private LocalDateTime verificationDate;

    @Size(max = 200, message = "Verifying legal counsel must not exceed 200 characters")
    private String verifyingLegalCounsel;
}
