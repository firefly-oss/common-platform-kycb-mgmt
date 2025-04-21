package com.catalis.core.kycb.interfaces.dtos.industry.v1;

import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for industry risk data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class IndustryRiskDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long industryRiskId;

    private String activityCode;
    private String industryName;
    private String inherentRiskLevel;
    private Integer riskScore;
    private String riskFactors;
    private String mitigatingFactors;
    private Boolean sepblacHighRisk;
    private Boolean euHighRisk;
    private Boolean fatfHighRisk;
    private Boolean cashIntensive;
    private Boolean complexStructures;
    private LocalDateTime assessmentDate;
    private String assessedBy;
    private LocalDateTime nextAssessmentDate;
    private Boolean requiresEdd;
}