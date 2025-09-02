package com.firefly.core.kycb.interfaces.dtos.risk.v1;

import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID riskAssessmentId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotBlank(message = "Assessment type is required")
    @Size(max = 50, message = "Assessment type must not exceed 50 characters")
    private String assessmentType;

    @NotNull(message = "Assessment date is required")
    @ValidDateTime
    private LocalDateTime assessmentDate;

    @NotBlank(message = "Risk category is required")
    @Size(max = 50, message = "Risk category must not exceed 50 characters")
    private String riskCategory;

    @NotNull(message = "Risk score is required")
    @Min(value = 0, message = "Risk score must be between 0 and 100")
    @Max(value = 100, message = "Risk score must be between 0 and 100")
    private Integer riskScore;

    @NotBlank(message = "Risk level is required")
    @Size(max = 20, message = "Risk level must not exceed 20 characters")
    private String riskLevel;

    @Size(max = 1000, message = "Risk factors must not exceed 1000 characters")
    private String riskFactors;

    @Size(max = 1000, message = "Assessment notes must not exceed 1000 characters")
    private String assessmentNotes;

    @NotBlank(message = "Assessment agent is required")
    @Size(max = 100, message = "Assessment agent must not exceed 100 characters")
    private String assessmentAgent;

    @ValidDateTime
    private LocalDateTime nextAssessmentDate;
}
