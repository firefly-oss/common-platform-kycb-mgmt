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
 * DTO for verification document data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class VerificationDocumentDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long verificationDocumentId;

    @FilterableId
    private Long kycVerificationId;

    @FilterableId
    private Long identityDocumentId;

    private String documentType;
    private String verificationPurpose;
    private String documentReference;
    private String documentSystemId;
    private Boolean isVerified;
    private String verificationNotes;
    @ValidDateTime
    private LocalDateTime expiryDate;
}
