package com.catalis.core.kycb.interfaces.dtos.risk.v1;

import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for risk assessment data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RiskAssessmentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long riskAssessmentId;

    @FilterableId
    private Long partyId;

    private String assessmentType;
    private LocalDateTime assessmentDate;
    private String riskCategory;
    private Integer riskScore;
    private String riskLevel;
    private String riskFactors;
    private String assessmentNotes;
    private String assessmentAgent;
    private LocalDateTime nextAssessmentDate;
}