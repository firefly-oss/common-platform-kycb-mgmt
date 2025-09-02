package com.firefly.core.kycb.interfaces.dtos.source.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidCurrencyCode;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
import java.util.UUID;

/**
 * DTO for source of funds data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SourceOfFundsDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID sourceOfFundsId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotBlank(message = "Source type is required")
    @Size(max = 50, message = "Source type must not exceed 50 characters")
    private String sourceType;

    @NotBlank(message = "Source description is required")
    @Size(max = 500, message = "Source description must not exceed 500 characters")
    private String sourceDescription;

    @NotNull(message = "Estimated annual amount is required")
    @ValidAmount
    private BigDecimal estimatedAnnualAmount;

    @NotBlank(message = "Currency is required")
    @ValidCurrencyCode
    private String currency;

    @NotNull(message = "Verified status is required")
    private Boolean isVerified;

    @Size(max = 50, message = "Verification method must not exceed 50 characters")
    private String verificationMethod;

    @Size(max = 500, message = "Supporting documents must not exceed 500 characters")
    private String supportingDocuments;

    @ValidDateTime
    private LocalDateTime verificationDate;
}
