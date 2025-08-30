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
    @NotNull(message = "Party ID is required")
    private Long partyId;

    @NotBlank(message = "Document type is required")
    @Size(max = 50, message = "Document type must not exceed 50 characters")
    private String documentType;

    @NotBlank(message = "Document reference is required")
    @Size(max = 100, message = "Document reference must not exceed 100 characters")
    private String documentReference;

    @FilterableId
    @Size(max = 100, message = "Document system ID must not exceed 100 characters")
    private String documentSystemId;

    @Size(max = 200, message = "Notary name must not exceed 200 characters")
    private String notaryName;

    @Size(max = 200, message = "Notary location must not exceed 200 characters")
    private String notaryLocation;

    @Size(max = 200, message = "Commercial registry must not exceed 200 characters")
    private String commercialRegistry;

    @Size(max = 100, message = "Registry entry must not exceed 100 characters")
    private String registryEntry;

    @NotNull(message = "Issue date is required")
    @ValidDateTime
    private LocalDateTime issueDate;

    @ValidDateTime
    private LocalDateTime registryDate;

    @ValidDateTime
    private LocalDateTime expiryDate;

    @NotNull(message = "Verified status is required")
    private Boolean isVerified;

    @Size(max = 1000, message = "Verification notes must not exceed 1000 characters")
    private String verificationNotes;

    @ValidDateTime
    private LocalDateTime verificationDate;

    @Size(max = 100, message = "Verification agent must not exceed 100 characters")
    private String verificationAgent;
}
