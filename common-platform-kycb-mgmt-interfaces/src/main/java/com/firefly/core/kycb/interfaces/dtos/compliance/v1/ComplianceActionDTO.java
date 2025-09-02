package com.firefly.core.kycb.interfaces.dtos.compliance.v1;

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
 * DTO for compliance action data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ComplianceActionDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID complianceActionId;

    @FilterableId
    @NotNull(message = "Compliance case ID is required")
    private UUID complianceCaseId;

    @NotBlank(message = "Action type is required")
    @Size(max = 50, message = "Action type must not exceed 50 characters")
    private String actionType;

    @NotBlank(message = "Action status is required")
    @Size(max = 50, message = "Action status must not exceed 50 characters")
    private String actionStatus;

    @NotBlank(message = "Action description is required")
    @Size(max = 1000, message = "Action description must not exceed 1000 characters")
    private String actionDescription;

    @NotBlank(message = "Action agent is required")
    @Size(max = 100, message = "Action agent must not exceed 100 characters")
    private String actionAgent;

    @NotNull(message = "Due date is required")
    @ValidDateTime
    private LocalDateTime dueDate;

    @ValidDateTime
    private LocalDateTime completionDate;

    @Size(max = 1000, message = "Result must not exceed 1000 characters")
    private String result;
}
