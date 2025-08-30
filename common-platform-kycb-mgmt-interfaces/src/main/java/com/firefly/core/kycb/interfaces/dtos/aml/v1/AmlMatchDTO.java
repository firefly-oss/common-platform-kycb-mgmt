package com.firefly.core.kycb.interfaces.dtos.aml.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
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
 * DTO for AML match data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AmlMatchDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long amlMatchId;

    @FilterableId
    @NotNull(message = "AML screening ID is required")
    private Long amlScreeningId;

    @NotBlank(message = "List type is required")
    @Size(max = 50, message = "List type must not exceed 50 characters")
    private String listType;

    @NotBlank(message = "List source is required")
    @Size(max = 100, message = "List source must not exceed 100 characters")
    private String listSource;

    @NotBlank(message = "Matched name is required")
    @Size(max = 200, message = "Matched name must not exceed 200 characters")
    private String matchedName;

    @NotNull(message = "Match score is required")
    @DecimalMin(value = "0.0", message = "Match score must be between 0.0 and 1.0")
    @DecimalMax(value = "1.0", message = "Match score must be between 0.0 and 1.0")
    @ValidAmount
    private BigDecimal matchScore;

    @Size(max = 1000, message = "Match details must not exceed 1000 characters")
    private String matchDetails;

    @NotBlank(message = "Resolution status is required")
    @Size(max = 50, message = "Resolution status must not exceed 50 characters")
    private String resolutionStatus;

    @Size(max = 1000, message = "Resolution notes must not exceed 1000 characters")
    private String resolutionNotes;

    @Size(max = 100, message = "Resolution agent must not exceed 100 characters")
    private String resolutionAgent;

    @ValidDateTime
    private LocalDateTime resolutionDate;
}
