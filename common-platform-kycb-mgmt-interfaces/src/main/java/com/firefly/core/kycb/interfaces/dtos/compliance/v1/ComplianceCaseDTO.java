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
 * DTO for compliance case data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ComplianceCaseDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID complianceCaseId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    @NotBlank(message = "Case type is required")
    @Size(max = 50, message = "Case type must not exceed 50 characters")
    private String caseType;

    @NotBlank(message = "Case status is required")
    @Size(max = 50, message = "Case status must not exceed 50 characters")
    private String caseStatus;

    @NotBlank(message = "Case priority is required")
    @Size(max = 20, message = "Case priority must not exceed 20 characters")
    private String casePriority;

    @NotBlank(message = "Case reference is required")
    @Size(max = 100, message = "Case reference must not exceed 100 characters")
    private String caseReference;

    @NotBlank(message = "Case summary is required")
    @Size(max = 2000, message = "Case summary must not exceed 2000 characters")
    private String caseSummary;

    @NotBlank(message = "Assigned to is required")
    @Size(max = 100, message = "Assigned to must not exceed 100 characters")
    private String assignedTo;

    @NotNull(message = "Due date is required")
    @ValidDateTime
    private LocalDateTime dueDate;

    @ValidDateTime
    private LocalDateTime resolutionDate;

    @Size(max = 2000, message = "Resolution notes must not exceed 2000 characters")
    private String resolutionNotes;

    @NotNull(message = "Report to SEPBLAC required flag is required")
    private Boolean reportToSepblacRequired;
}
