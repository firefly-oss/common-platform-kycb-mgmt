package com.catalis.core.kycb.interfaces.dtos.edd.v1;

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
 * DTO for enhanced due diligence data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class EnhancedDueDiligenceDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long eddId;

    @FilterableId
    private Long kycVerificationId;

    private String eddReason;
    private String eddStatus;
    private String eddDescription;
    private String approvingAuthority;
    private LocalDateTime approvalDate;
    private String eddNotes;
    private Boolean internalCommitteeApproval;
    private LocalDateTime committeeApprovalDate;
    private LocalDateTime completionDate;
    private String completedBy;
}