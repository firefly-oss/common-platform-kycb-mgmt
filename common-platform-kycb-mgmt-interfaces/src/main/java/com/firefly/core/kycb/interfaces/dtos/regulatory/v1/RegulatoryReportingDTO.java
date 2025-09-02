package com.firefly.core.kycb.interfaces.dtos.regulatory.v1;

import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
 * DTO for regulatory reporting data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegulatoryReportingDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID reportId;

    @FilterableId
    @NotNull(message = "Compliance case ID is required")
    private UUID complianceCaseId;

    @NotBlank(message = "Report type is required")
    @Size(max = 50, message = "Report type must not exceed 50 characters")
    private String reportType;

    @NotBlank(message = "Report reference is required")
    @Size(max = 100, message = "Report reference must not exceed 100 characters")
    private String reportReference;

    @NotBlank(message = "Regulatory authority is required")
    @Size(max = 200, message = "Regulatory authority must not exceed 200 characters")
    private String regulatoryAuthority;

    @NotBlank(message = "Report status is required")
    @Size(max = 50, message = "Report status must not exceed 50 characters")
    private String reportStatus;

    @NotNull(message = "Submission date is required")
    @ValidDateTime
    private LocalDateTime submissionDate;

    @NotBlank(message = "Submitting agent is required")
    @Size(max = 100, message = "Submitting agent must not exceed 100 characters")
    private String submittingAgent;

    @ValidDateTime
    private LocalDateTime acknowledgmentDate;

    @NotBlank(message = "Report content summary is required")
    @Size(max = 2000, message = "Report content summary must not exceed 2000 characters")
    private String reportContentSummary;
}
