package com.firefly.core.kycb.interfaces.dtos.corporate.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDateTime;
import com.firefly.annotations.ValidInterestRate;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long partyId;

    @FilterableId
    private Long parentEntityId;

    @ValidInterestRate
    private BigDecimal ownershipPercentage;
    private String relationshipType;
    private String controlNotes;
    private Boolean isVerified;
    @ValidDateTime
    private LocalDateTime verificationDate;
    @ValidDateTime
    private LocalDateTime startDate;
    @ValidDateTime
    private LocalDateTime endDate;
}
