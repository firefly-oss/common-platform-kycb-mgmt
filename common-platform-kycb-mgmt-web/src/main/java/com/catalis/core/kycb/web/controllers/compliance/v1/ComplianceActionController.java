package com.catalis.core.kycb.web.controllers.compliance.v1;

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
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
            description = "Retrieves all actions for the specified compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved compliance actions",
                            content = @Content(schema = @Schema(implementation = ComplianceActionDTO.class))
                    )
            }
    )
    public Flux<ComplianceActionDTO> listCaseActions(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId
    ) {
        return complianceActionService.findByCaseId(caseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public Mono<ComplianceActionDTO> createCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Compliance action data", required = true)
            @RequestBody ComplianceActionDTO complianceActionDTO
    ) {
        complianceActionDTO.setComplianceCaseId(caseId);
        return complianceActionService.create(complianceActionDTO);
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
    public Mono<ComplianceActionDTO> getCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the action", required = true)
            @PathVariable Long actionId
    ) {
        return complianceActionService.getById(actionId);
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
    public Mono<ComplianceActionDTO> updateCaseAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the action", required = true)
            @PathVariable Long actionId,
            @Parameter(description = "Updated compliance action data", required = true)
            @RequestBody ComplianceActionDTO complianceActionDTO
    ) {
        complianceActionDTO.setComplianceCaseId(caseId);
        return complianceActionService.update(actionId, complianceActionDTO);
    }

    @PostMapping("/{actionId}/complete")
    @Operation(
            summary = "Complete action",
            description = "Marks a compliance action as completed",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully completed compliance action",
                            content = @Content(schema = @Schema(implementation = ComplianceActionDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Compliance action not found"
                    )
            }
    )
    public Mono<ComplianceActionDTO> completeAction(
            @Parameter(description = "ID of the case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the action", required = true)
            @PathVariable Long actionId,
            @Parameter(description = "Completion notes")
            @RequestParam(required = false) String completionNotes,
            @Parameter(description = "Completed by", required = true)
            @RequestParam String completedBy
    ) {
        return complianceActionService.completeAction(actionId, completionNotes, completedBy);
    }
}