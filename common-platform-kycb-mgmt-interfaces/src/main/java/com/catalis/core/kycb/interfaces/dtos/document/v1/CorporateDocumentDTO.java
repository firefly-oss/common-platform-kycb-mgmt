package com.catalis.core.kycb.interfaces.dtos.document.v1;

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
 * DTO for corporate document data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CorporateDocumentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long corporateDocumentId;

    @FilterableId
    private Long partyId;

    private String documentType;
    private String documentReference;

    @FilterableId
    private String documentSystemId;

    private String notaryName;
    private String notaryLocation;
    private String commercialRegistry;
    private String registryEntry;
    @ValidDateTime
    private LocalDateTime issueDate;
    @ValidDateTime
    private LocalDateTime registryDate;
    @ValidDateTime
    private LocalDateTime expiryDate;
    private Boolean isVerified;
    private String verificationNotes;
    @ValidDateTime
    private LocalDateTime verificationDate;
    private String verificationAgent;
}
