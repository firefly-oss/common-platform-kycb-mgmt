package com.catalis.core.kycb.interfaces.dtos.business.v1;

import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.math.BigDecimal;

/**
 * DTO for business profile data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessProfileDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long businessProfileId;

    @FilterableId
    private Long partyId;

    private String legalFormCode;
    private String businessDescription;
    private String websiteUrl;
    private Integer incorporationYear;
    private Integer employeeCount;
    private String employeeRangeCode;
    @ValidAmount
    private BigDecimal annualRevenue;
    @ValidCurrencyCode
    private String currencyIsoCode;
    private String revenueRangeCode;
    private String stockExchange;
    private String stockSymbol;
    private Boolean isRegulated;
    private String regulatoryAuthority;
    private String companyStatusCode;
    private String companySizeCode;
    private Boolean isPublicEntity;
    private String registrationNumber;
}
