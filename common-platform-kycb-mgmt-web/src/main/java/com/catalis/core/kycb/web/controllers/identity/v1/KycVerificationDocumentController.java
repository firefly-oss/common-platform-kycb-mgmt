package com.catalis.core.kycb.web.controllers.identity.v1;

import com.catalis.core.kycb.core.services.document.v1.VerificationDocumentService;
import com.catalis.core.kycb.interfaces.dtos.document.v1.VerificationDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/identity/parties/{partyId}/kyc/{verificationId}/documents")
@Tag(name = "KYC Verification Documents", description = "API for managing KYC verification documents")
public class KycVerificationDocumentController {

    @Autowired
    private VerificationDocumentService verificationDocumentService;

    @GetMapping
    @Operation(
            summary = "List verification documents",
            description = "Retrieves all verification documents for the specified KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved verification documents",
                            content = @Content(schema = @Schema(implementation = VerificationDocumentDTO.class))
                    )
            }
    )
    public Flux<VerificationDocumentDTO> listVerificationDocuments(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId
    ) {
        return verificationDocumentService.findByKycVerificationId(verificationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add verification document",
            description = "Adds a new verification document to the specified KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added verification document",
                            content = @Content(schema = @Schema(implementation = VerificationDocumentDTO.class))
                    )
            }
    )
    public Mono<VerificationDocumentDTO> addVerificationDocument(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "Verification document data", required = true)
            @RequestBody VerificationDocumentDTO verificationDocumentDTO
    ) {
        verificationDocumentDTO.setKycVerificationId(verificationId);
        return verificationDocumentService.create(verificationDocumentDTO);
    }
}