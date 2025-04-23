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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/identity/parties/{partyId}/kyc/{verificationId}/edd")
@Tag(name = "Enhanced Due Diligence", description = "API for managing enhanced due diligence processes")
public class EnhancedDueDiligenceController {

    @Autowired
    private EnhancedDueDiligenceService enhancedDueDiligenceService;

    @GetMapping
    @Operation(
            summary = "List EDD processes",
            description = "Retrieves all enhanced due diligence processes for the specified KYC verification with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved EDD processes",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<EnhancedDueDiligenceDTO>>> listEddProcesses(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<EnhancedDueDiligenceDTO> filterRequest
    ) {
        // Set verification ID filter
        EnhancedDueDiligenceDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new EnhancedDueDiligenceDTO();
        filter.setKycVerificationId(verificationId);
        filterRequest.setFilters(filter);

        return enhancedDueDiligenceService.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
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
    public Mono<ResponseEntity<EnhancedDueDiligenceDTO>> startEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "EDD process data", required = true)
            @RequestBody EnhancedDueDiligenceDTO enhancedDueDiligenceDTO
    ) {
        enhancedDueDiligenceDTO.setKycVerificationId(verificationId);
        return enhancedDueDiligenceService.create(enhancedDueDiligenceDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
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
    public Mono<ResponseEntity<EnhancedDueDiligenceDTO>> updateEddProcess(
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
        return enhancedDueDiligenceService.update(eddId, enhancedDueDiligenceDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<EnhancedDueDiligenceDTO>> getEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId
    ) {
        return enhancedDueDiligenceService.getById(eddId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{eddId}")
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
    public Mono<ResponseEntity<Void>> deleteEddProcess(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the KYC verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "ID of the EDD process", required = true)
            @PathVariable Long eddId
    ) {
        return enhancedDueDiligenceService.delete(eddId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
