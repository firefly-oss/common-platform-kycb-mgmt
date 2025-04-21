package com.catalis.core.kycb.interfaces.dtos.economic.v1;

import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * DTO for economic activity data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EconomicActivityDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long economicActivityId;

    @FilterableId
    private Long partyId;

    private String activityCode;
    private Boolean isPrimary;
    private String sectorCode;
    private String subsector;
    private Boolean highRiskActivity;
    private String activityDetails;
    private String geographicScopeCode;
    private String exportMarkets;
    private String importMarkets;
    private Boolean regulatedActivity;
    private String regulatoryDetails;
}
