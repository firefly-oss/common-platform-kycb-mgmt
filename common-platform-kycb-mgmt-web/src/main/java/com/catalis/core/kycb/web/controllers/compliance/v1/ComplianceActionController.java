package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.compliance.v1.ComplianceActionService;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceActionDTO;
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
@RequestMapping("/api/v1/compliance/cases/{caseId}/actions")
@Tag(name = "Compliance Actions", description = "API for managing compliance case actions")
public class ComplianceActionController {

    @Autowired
    private ComplianceActionService complianceActionService;

    @GetMapping
    @Operation(
            summary = "List case actions",
            description = "Retrieves all actions for the specified compliance case with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance actions",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<ComplianceActionDTO>>> listCaseActions(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<ComplianceActionDTO> filterRequest
    ) {
        // Set case ID filter
        ComplianceActionDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new ComplianceActionDTO();
        filter.setComplianceCaseId(caseId);
        filterRequest.setFilters(filter);

        return complianceActionService.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(
            summary = "Create case action",
            description = "Creates a new action for the specified compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created compliance action",
                            content = @Content(schema = @Schema(implementation = ComplianceActionDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<ComplianceActionDTO>> createCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Compliance action data", required = true)
            @RequestBody ComplianceActionDTO complianceActionDTO
    ) {
        complianceActionDTO.setComplianceCaseId(caseId);
        return complianceActionService.create(complianceActionDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{actionId}")
    @Operation(
            summary = "Get specific action",
            description = "Retrieves a specific compliance action by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance action",
                            content = @Content(schema = @Schema(implementation = ComplianceActionDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance action not found"
                    )
            }
    )
    public Mono<ResponseEntity<ComplianceActionDTO>> getCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the action", required = true)
            @PathVariable Long actionId
    ) {
        return complianceActionService.getById(actionId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{actionId}")
    @Operation(
            summary = "Update case action",
            description = "Updates an existing compliance action",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated compliance action",
                            content = @Content(schema = @Schema(implementation = ComplianceActionDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance action not found"
                    )
            }
    )
    public Mono<ResponseEntity<ComplianceActionDTO>> updateCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the action", required = true)
            @PathVariable Long actionId,
            @Parameter(description = "Updated compliance action data", required = true)
            @RequestBody ComplianceActionDTO complianceActionDTO
    ) {
        complianceActionDTO.setComplianceCaseId(caseId);
        return complianceActionService.update(actionId, complianceActionDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{actionId}")
    @Operation(
            summary = "Delete case action",
            description = "Deletes a compliance action",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted compliance action"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance action not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the action", required = true)
            @PathVariable Long actionId
    ) {
        return complianceActionService.delete(actionId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
