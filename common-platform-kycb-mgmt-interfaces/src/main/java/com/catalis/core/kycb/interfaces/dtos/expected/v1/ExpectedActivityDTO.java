package com.catalis.core.kycb.interfaces.dtos.expected.v1;

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
 * DTO for expected activity data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExpectedActivityDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long expectedActivityId;

    @FilterableId
    private Long partyId;

    private String activityTypeCode;
    private BigDecimal expectedMonthlyVolume;
    private BigDecimal expectedAnnualVolume;
    private Integer expectedTransactionCount;
    private String currencyIsoCode;
    private String transactionFreqCode;
    private String anticipatedCountries;
    private Boolean isHighValue;
    private String highValueDetails;
    private String expectedCounterparties;
    private Boolean cashIntensive;
    private Boolean taxHavenTransactions;
    private String declarationModelCode;
    private String declarationDetails;
    private String verificationNotes;
}