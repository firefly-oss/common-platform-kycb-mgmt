package com.catalis.core.kycb.interfaces.dtos.regulatory.v1;

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
 * DTO for regulatory reporting data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegulatoryReportingDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long reportId;

    @FilterableId
    private Long complianceCaseId;

    private String reportType;
    private String reportReference;
    private String regulatoryAuthority;
    private String reportStatus;
    private LocalDateTime submissionDate;
    private String submittingAgent;
    private LocalDateTime acknowledgmentDate;
    private String reportContentSummary;
}