package com.firefly.core.kycb.interfaces.dtos.document.v1;

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
    @NotNull(message = "KYC verification ID is required")
    private Long kycVerificationId;

    @FilterableId
    @NotNull(message = "Identity document ID is required")
    private Long identityDocumentId;

    @NotBlank(message = "Document type is required")
    @Size(max = 50, message = "Document type must not exceed 50 characters")
    private String documentType;

    @NotBlank(message = "Verification purpose is required")
    @Size(max = 50, message = "Verification purpose must not exceed 50 characters")
    private String verificationPurpose;

    @NotBlank(message = "Document reference is required")
    @Size(max = 100, message = "Document reference must not exceed 100 characters")
    private String documentReference;

    @Size(max = 100, message = "Document system ID must not exceed 100 characters")
    private String documentSystemId;

    @NotNull(message = "Verified status is required")
    private Boolean isVerified;

    @Size(max = 1000, message = "Verification notes must not exceed 1000 characters")
    private String verificationNotes;

    @ValidDateTime
    private LocalDateTime expiryDate;
}
