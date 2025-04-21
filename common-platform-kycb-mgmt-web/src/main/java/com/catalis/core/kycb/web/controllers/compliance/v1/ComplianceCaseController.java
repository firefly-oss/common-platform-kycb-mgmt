package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.compliance.v1.ComplianceCaseService;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
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
@RequestMapping("/api/v1/compliance/cases")
@Tag(name = "Compliance Cases", description = "API for managing compliance cases")
public class ComplianceCaseController {

    @Autowired
    private ComplianceCaseService complianceCaseService;

    @GetMapping
    @Operation(
            summary = "List compliance cases",
            description = "Retrieves all compliance cases with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance cases",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<PaginationResponse<ComplianceCaseDTO>> listComplianceCases(
            @Parameter(description = "Filter request", required = false)
            @ModelAttribute FilterRequest<ComplianceCaseDTO> filterRequest
    ) {
        return complianceCaseService.findAll(filterRequest);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create new compliance case",
            description = "Creates a new compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created compliance case",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    )
            }
    )
    public Mono<ComplianceCaseDTO> createComplianceCase(
            @Parameter(description = "Compliance case data", required = true)
            @RequestBody ComplianceCaseDTO complianceCaseDTO
    ) {
        return complianceCaseService.create(complianceCaseDTO);
    }

    @GetMapping("/{caseId}")
    @Operation(
            summary = "Get specific case",
            description = "Retrieves a specific compliance case by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance case",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance case not found"
                    )
            }
    )
    public Mono<ComplianceCaseDTO> getComplianceCase(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId
    ) {
        return complianceCaseService.getById(caseId);
    }

    @PatchMapping("/{caseId}")
    @Operation(
            summary = "Update case",
            description = "Updates an existing compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated compliance case",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance case not found"
                    )
            }
    )
    public Mono<ComplianceCaseDTO> updateComplianceCase(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Updated compliance case data", required = true)
            @RequestBody ComplianceCaseDTO complianceCaseDTO
    ) {
        return complianceCaseService.update(caseId, complianceCaseDTO);
    }

    @PostMapping("/{caseId}/assign")
    @Operation(
            summary = "Assign case",
            description = "Assigns a compliance case to a specific agent",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully assigned compliance case",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance case not found"
                    )
            }
    )
    public Mono<ComplianceCaseDTO> assignComplianceCase(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Agent to assign the case to", required = true)
            @RequestParam String assignedTo
    ) {
        return complianceCaseService.assignCase(caseId, assignedTo);
    }

    @PostMapping("/{caseId}/status")
    @Operation(
            summary = "Update case status",
            description = "Updates the status of a compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated compliance case status",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance case not found"
                    )
            }
    )
    public Mono<ComplianceCaseDTO> updateComplianceCaseStatus(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "New status", required = true)
            @RequestParam String status,
            @Parameter(description = "Status notes")
            @RequestParam(required = false) String statusNotes
    ) {
        return complianceCaseService.updateStatus(caseId, status, statusNotes);
    }

    @GetMapping("/by-party/{partyId}")
    @Operation(
            summary = "List cases by party",
            description = "Retrieves all compliance cases for a specific party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance cases",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    )
            }
    )
    public Flux<ComplianceCaseDTO> listComplianceCasesByParty(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return complianceCaseService.findByPartyId(partyId);
    }

    @GetMapping("/by-status/{status}")
    @Operation(
            summary = "List cases by status",
            description = "Retrieves all compliance cases with a specific status",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance cases",
                            content = @Content(schema = @Schema(implementation = ComplianceCaseDTO.class))
                    )
            }
    )
    public Flux<ComplianceCaseDTO> listComplianceCasesByStatus(
            @Parameter(description = "Status", required = true)
            @PathVariable String status
    ) {
        return complianceCaseService.findByStatus(status);
    }
}
