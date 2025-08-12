package com.catalis.core.kycb.interfaces.dtos.compliance.v1;

import com.catalis.annotations.ValidDateTime;
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
 * DTO for compliance action data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ComplianceActionDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long complianceActionId;

    @FilterableId
    private Long complianceCaseId;

    private String actionType;
    private String actionStatus;
    private String actionDescription;
    private String actionAgent;
    @ValidDateTime
    private LocalDateTime dueDate;
    @ValidDateTime
    private LocalDateTime completionDate;
    private String result;
}
