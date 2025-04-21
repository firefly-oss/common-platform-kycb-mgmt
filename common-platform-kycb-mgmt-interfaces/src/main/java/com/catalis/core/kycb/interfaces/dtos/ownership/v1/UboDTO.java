package com.catalis.core.kycb.interfaces.dtos.ownership.v1;

import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * DTO for Ultimate Beneficial Owner data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UboDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long uboId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long naturalPersonId;

    private BigDecimal ownershipPercentage;
    private String ownershipType;
    private String controlStructure;
    private Boolean isVerified;
    private String verificationMethod;
    private String titularidadRealDocument;
    private LocalDateTime verificationDate;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
