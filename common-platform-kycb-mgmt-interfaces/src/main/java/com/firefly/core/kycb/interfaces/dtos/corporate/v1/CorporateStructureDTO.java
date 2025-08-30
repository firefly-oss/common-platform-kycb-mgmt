package com.firefly.core.kycb.interfaces.dtos.corporate.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDateTime;
import com.firefly.annotations.ValidInterestRate;
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

/**
 * DTO for corporate structure data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CorporateStructureDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long corporateStructureId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private Long partyId;

    @FilterableId
    @NotNull(message = "Parent entity ID is required")
    private Long parentEntityId;

    @NotNull(message = "Ownership percentage is required")
    @ValidInterestRate
    private BigDecimal ownershipPercentage;

    @NotBlank(message = "Relationship type is required")
    @Size(max = 50, message = "Relationship type must not exceed 50 characters")
    private String relationshipType;

    @Size(max = 1000, message = "Control notes must not exceed 1000 characters")
    private String controlNotes;

    @NotNull(message = "Verified status is required")
    private Boolean isVerified;

    @ValidDateTime
    private LocalDateTime verificationDate;

    @NotNull(message = "Start date is required")
    @ValidDateTime
    private LocalDateTime startDate;

    @ValidDateTime
    private LocalDateTime endDate;
}
