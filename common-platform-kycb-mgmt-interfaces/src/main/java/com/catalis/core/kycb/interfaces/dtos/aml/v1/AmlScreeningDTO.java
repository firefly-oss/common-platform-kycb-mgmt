package com.catalis.core.kycb.interfaces.dtos.aml.v1;

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
 * DTO for AML screening data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AmlScreeningDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long amlScreeningId;

    @FilterableId
    private Long partyId;

    private LocalDateTime screeningDate;
    private String screeningType;
    private Boolean matchesFound;
    private Integer matchCount;
    private String screeningProvider;

    @FilterableId
    private String referenceId;

    private String screeningResult;
    private LocalDateTime nextScreeningDate;
}