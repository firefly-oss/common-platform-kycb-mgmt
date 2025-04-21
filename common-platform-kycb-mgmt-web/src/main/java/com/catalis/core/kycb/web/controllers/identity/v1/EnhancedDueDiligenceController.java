package com.catalis.core.kycb.web.controllers.identity.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.edd.v1.EnhancedDueDiligenceService;
import com.catalis.core.kycb.interfaces.dtos.edd.v1.EnhancedDueDiligenceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/identity/parties/{partyId}/kyc/{verificationId}/edd")
@Tag(name = "Enhanced Due Diligence", description = "API for managing enhanced due diligence processes")
public class EnhancedDueDiligenceController {

    @Autowired
    private EnhancedDueDiligenceService enhancedDueDiligenceService;

    @GetMapping
    @Operation(
            summary = "List EDD processes",
            description = "Retrieves all enhanced due diligence processes for the specified KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved EDD processes",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    )
            }
    )
    public Flux<EnhancedDueDiligenceDTO> listEddProcesses(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId
    ) {
        return enhancedDueDiligenceService.findByKycVerificationId(verificationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Start new EDD process",
            description = "Creates a new enhanced due diligence process for the specified KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> startEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "EDD process data", required = true)
            @RequestBody EnhancedDueDiligenceDTO enhancedDueDiligenceDTO
    ) {
        enhancedDueDiligenceDTO.setKycVerificationId(verificationId);
        return enhancedDueDiligenceService.create(enhancedDueDiligenceDTO);
    }

    @PatchMapping("/{eddId}")
    @Operation(
            summary = "Update EDD process",
            description = "Updates an existing enhanced due diligence process",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "EDD process not found"
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> updateEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId,
            @Parameter(description = "Updated EDD process data", required = true)
            @RequestBody EnhancedDueDiligenceDTO enhancedDueDiligenceDTO
    ) {
        enhancedDueDiligenceDTO.setKycVerificationId(verificationId);
        return enhancedDueDiligenceService.update(eddId, enhancedDueDiligenceDTO);
    }

    @GetMapping("/{eddId}")
    @Operation(
            summary = "Get specific EDD process",
            description = "Retrieves a specific enhanced due diligence process by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "EDD process not found"
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> getEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId
    ) {
        return enhancedDueDiligenceService.getById(eddId);
    }

    @DeleteMapping("/{eddId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete EDD process",
            description = "Deletes an enhanced due diligence process",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted EDD process"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "EDD process not found"
                    )
            }
    )
    public Mono<Void> deleteEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId
    ) {
        return enhancedDueDiligenceService.delete(eddId);
    }

    @PostMapping("/{eddId}/complete")
    @Operation(
            summary = "Complete EDD process",
            description = "Marks an enhanced due diligence process as completed",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully completed EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "EDD process not found"
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> completeEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId,
            @Parameter(description = "User completing the EDD", required = true)
            @RequestParam String completedBy,
            @Parameter(description = "Completion notes")
            @RequestParam(required = false) String completionNotes
    ) {
        return enhancedDueDiligenceService.completeEdd(eddId, completedBy, completionNotes);
    }

    @PostMapping("/{eddId}/approve")
    @Operation(
            summary = "Approve EDD process",
            description = "Approves an enhanced due diligence process",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully approved EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "EDD process not found"
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> approveEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId,
            @Parameter(description = "Approving authority", required = true)
            @RequestParam String approvingAuthority,
            @Parameter(description = "Approval notes")
            @RequestParam(required = false) String approvalNotes
    ) {
        return enhancedDueDiligenceService.approveEdd(eddId, approvingAuthority, approvalNotes);
    }

    @PostMapping("/{eddId}/waive")
    @Operation(
            summary = "Waive EDD process",
            description = "Waives an enhanced due diligence process",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully waived EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "EDD process not found"
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> waiveEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId,
            @Parameter(description = "Waive reason", required = true)
            @RequestParam String waiveReason,
            @Parameter(description = "Waive authority", required = true)
            @RequestParam String waiveAuthority
    ) {
        return enhancedDueDiligenceService.waiveEdd(eddId, waiveReason, waiveAuthority);
    }

    @GetMapping("/status/{status}")
    @Operation(
            summary = "Get EDD processes by status",
            description = "Retrieves all enhanced due diligence processes with a specific status for the KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved EDD processes",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    )
            }
    )
    public Flux<EnhancedDueDiligenceDTO> getEddProcessesByStatus(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "Status of the EDD processes", required = true)
            @PathVariable String status
    ) {
        return enhancedDueDiligenceService.findByEddStatus(status)
                .filter(edd -> edd.getKycVerificationId().equals(verificationId));
    }

    @GetMapping("/reason/{reason}")
    @Operation(
            summary = "Get EDD processes by reason",
            description = "Retrieves all enhanced due diligence processes with a specific reason for the KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved EDD processes",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    )
            }
    )
    public Flux<EnhancedDueDiligenceDTO> getEddProcessesByReason(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "Reason for the EDD processes", required = true)
            @PathVariable String reason
    ) {
        return enhancedDueDiligenceService.findByEddReason(reason)
                .filter(edd -> edd.getKycVerificationId().equals(verificationId));
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest EDD process",
            description = "Retrieves the latest enhanced due diligence process for the KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved latest EDD process",
                            content = @Content(schema = @Schema(implementation = EnhancedDueDiligenceDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No EDD processes found for KYC verification"
                    )
            }
    )
    public Mono<EnhancedDueDiligenceDTO> getLatestEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId
    ) {
        return enhancedDueDiligenceService.getLatestByKycVerificationId(verificationId);
    }
}