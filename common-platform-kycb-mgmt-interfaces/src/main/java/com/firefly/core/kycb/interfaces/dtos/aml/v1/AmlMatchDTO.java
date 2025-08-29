package com.firefly.core.kycb.interfaces.dtos.aml.v1;

import com.firefly.annotations.ValidAmount;
import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Long amlScreeningId;

    private String listType;
    private String listSource;
    private String matchedName;
    @ValidAmount
    private BigDecimal matchScore;
    private String matchDetails;
    private String resolutionStatus;
    private String resolutionNotes;
    private String resolutionAgent;
    @ValidDateTime
    private LocalDateTime resolutionDate;
}
