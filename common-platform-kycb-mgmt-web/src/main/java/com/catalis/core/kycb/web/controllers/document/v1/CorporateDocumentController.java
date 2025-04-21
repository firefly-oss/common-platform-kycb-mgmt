package com.catalis.core.kycb.web.controllers.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.document.v1.CorporateDocumentService;
import com.catalis.core.kycb.interfaces.dtos.document.v1.CorporateDocumentDTO;
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
@RequestMapping("/api/v1/documents/corporate")
@Tag(name = "Corporate Documents", description = "API for managing corporate documents")
public class CorporateDocumentController {

    @Autowired
    private CorporateDocumentService corporateDocumentService;

    @GetMapping("/{documentId}")
    @Operation(
            summary = "Get specific corporate document",
            description = "Retrieves a specific corporate document by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved corporate document",
                            content = @Content(schema = @Schema(implementation = CorporateDocumentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Corporate document not found"
                    )
            }
    )
    public Mono<CorporateDocumentDTO> getCorporateDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return corporateDocumentService.getById(documentId);
    }

    @PatchMapping("/{documentId}")
    @Operation(
            summary = "Update corporate document",
            description = "Updates an existing corporate document",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated corporate document",
                            content = @Content(schema = @Schema(implementation = CorporateDocumentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Corporate document not found"
                    )
            }
    )
    public Mono<CorporateDocumentDTO> updateCorporateDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId,
            @Parameter(description = "Updated corporate document data", required = true)
            @RequestBody CorporateDocumentDTO corporateDocumentDTO
    ) {
        return corporateDocumentService.update(documentId, corporateDocumentDTO);
    }

    @DeleteMapping("/{documentId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete corporate document",
            description = "Deletes a corporate document",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted corporate document"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Corporate document not found"
                    )
            }
    )
    public Mono<Void> deleteCorporateDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId
    ) {
        return corporateDocumentService.delete(documentId);
    }

    @PostMapping("/{documentId}/verify")
    @Operation(
            summary = "Verify document",
            description = "Marks a corporate document as verified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully verified document",
                            content = @Content(schema = @Schema(implementation = CorporateDocumentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Corporate document not found"
                    )
            }
    )
    public Mono<CorporateDocumentDTO> verifyDocument(
            @Parameter(description = "ID of the document", required = true)
            @PathVariable Long documentId,
            @Parameter(description = "Verification notes")
            @RequestParam(required = false) String verificationNotes
    ) {
        return corporateDocumentService.verifyDocument(documentId, verificationNotes);
    }

    @GetMapping("/party/{partyId}")
    @Operation(
            summary = "List corporate documents",
            description = "Retrieves all corporate documents for a specific party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved corporate documents",
                            content = @Content(schema = @Schema(implementation = CorporateDocumentDTO.class))
                    )
            }
    )
    public Flux<CorporateDocumentDTO> listCorporateDocuments(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return corporateDocumentService.findByPartyId(partyId);
    }

    @PostMapping("/party/{partyId}")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add corporate document",
            description = "Adds a new corporate document to the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added corporate document",
                            content = @Content(schema = @Schema(implementation = CorporateDocumentDTO.class))
                    )
            }
    )
    public Mono<CorporateDocumentDTO> addCorporateDocument(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Corporate document data", required = true)
            @RequestBody CorporateDocumentDTO corporateDocumentDTO
    ) {
        corporateDocumentDTO.setPartyId(partyId);
        return corporateDocumentService.create(corporateDocumentDTO);
    }

    @GetMapping("/type/{documentType}")
    @Operation(
            summary = "List documents by type",
            description = "Retrieves all corporate documents of a specific type",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved corporate documents",
                            content = @Content(schema = @Schema(implementation = CorporateDocumentDTO.class))
                    )
            }
    )
    public Flux<CorporateDocumentDTO> listDocumentsByType(
            @Parameter(description = "Type of document", required = true)
            @PathVariable String documentType
    ) {
        return corporateDocumentService.findByDocumentType(documentType);
    }
}
