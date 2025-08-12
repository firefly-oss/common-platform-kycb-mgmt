package com.catalis.core.kycb.interfaces.dtos.power.v1;

import com.catalis.annotations.ValidAmount;
import com.catalis.annotations.ValidCurrencyCode;
import com.catalis.annotations.ValidDateTime;
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
    private Long corporateDocumentId;

    @FilterableId
    private Long partyId;

    @FilterableId
    private Long attorneyId;

    private String powerType;
    private String powerScope;
    private Boolean jointSignatureRequired;
    private Integer jointSignatureCount;
    private String jointSignatureNotes;
    @ValidAmount
    private BigDecimal financialLimit;
    @ValidCurrencyCode
    private String currency;
    @ValidDateTime
    private LocalDateTime effectiveDate;
    @ValidDateTime
    private LocalDateTime expiryDate;
    private Boolean isVerified;
    private Boolean isBastanteoCompleted;
    private String verificationMethod;
    @ValidDateTime
    private LocalDateTime verificationDate;
    private String verifyingLegalCounsel;
}
