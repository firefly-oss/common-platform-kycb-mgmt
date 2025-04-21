package com.catalis.core.kycb.web.controllers.document.v1;

import com.catalis.core.kycb.core.services.document.v1.VerificationDocumentService;
import com.catalis.core.kycb.interfaces.dtos.document.v1.VerificationDocumentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/documents/verification")
@Tag(name = "Verification Documents", description = "API for managing verification documents")
public class VerificationDocumentController {

    @Autowired
    private VerificationDocumentService verificationDocumentService;

    @GetMapping("/{documentId}")
    @Operation(
            summary = "Get verification document",
            description = "Retrieves a specific verification document by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved verification document",
                            content = @Content(schema = @Schema(implementation = VerificationDocumentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Verification document not found"
                    )
            }
    )
    public Mono<VerificationDocumentDTO> getVerificationDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return verificationDocumentService.getById(documentId);
    }

    @PatchMapping("/{documentId}")
    @Operation(
            summary = "Update verification document",
            description = "Updates an existing verification document",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated verification document",
                            content = @Content(schema = @Schema(implementation = VerificationDocumentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Verification document not found"
                    )
            }
    )
    public Mono<VerificationDocumentDTO> updateVerificationDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId,
            @Parameter(description = "Updated verification document data", required = true)
            @RequestBody VerificationDocumentDTO verificationDocumentDTO
    ) {
        return verificationDocumentService.update(documentId, verificationDocumentDTO);
    }

    @DeleteMapping("/{documentId}")
    @Operation(
            summary = "Delete verification document",
            description = "Deletes a verification document",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted verification document"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Verification document not found"
                    )
            }
    )
    public Mono<Void> deleteVerificationDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return verificationDocumentService.delete(documentId);
    }

    @PostMapping("/{documentId}/verify")
    @Operation(
            summary = "Verify document",
            description = "Marks a verification document as verified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully verified document",
                            content = @Content(schema = @Schema(implementation = VerificationDocumentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Verification document not found"
                    )
            }
    )
    public Mono<VerificationDocumentDTO> verifyDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return verificationDocumentService.verify(documentId);
    }
}