package com.catalis.core.kycb.interfaces.dtos.compliance.v1;

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
 * DTO for compliance case data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ComplianceCaseDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long complianceCaseId;

    @FilterableId
    private Long partyId;

    private String caseType;
    private String caseStatus;
    private String casePriority;
    private String caseReference;
    private String caseSummary;
    private String assignedTo;
    private LocalDateTime dueDate;
    private LocalDateTime resolutionDate;
    private String resolutionNotes;
    private Boolean reportToSepblacRequired;
}