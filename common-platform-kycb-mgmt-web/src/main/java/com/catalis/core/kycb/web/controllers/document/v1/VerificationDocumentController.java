package com.catalis.core.kycb.web.controllers.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/documents/verification")
@Tag(name = "Verification Documents", description = "API for managing verification documents")
public class VerificationDocumentController {

    @Autowired
    private VerificationDocumentService verificationDocumentService;

    @GetMapping
    @Operation(
            summary = "List verification documents",
            description = "Retrieves all verification documents with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved verification documents",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<VerificationDocumentDTO>>> listVerificationDocuments(
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<VerificationDocumentDTO> filterRequest
    ) {
        return verificationDocumentService.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create verification document",
            description = "Creates a new verification document",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created verification document",
                            content = @Content(schema = @Schema(implementation = VerificationDocumentDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<VerificationDocumentDTO>> createVerificationDocument(
            @Parameter(description = "Verification document data", required = true)
            @RequestBody VerificationDocumentDTO verificationDocumentDTO
    ) {
        return verificationDocumentService.create(verificationDocumentDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

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
    public Mono<ResponseEntity<VerificationDocumentDTO>> getVerificationDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return verificationDocumentService.getById(documentId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<VerificationDocumentDTO>> updateVerificationDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId,
            @Parameter(description = "Updated verification document data", required = true)
            @RequestBody VerificationDocumentDTO verificationDocumentDTO
    ) {
        return verificationDocumentService.update(documentId, verificationDocumentDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<Void>> deleteVerificationDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return verificationDocumentService.delete(documentId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
