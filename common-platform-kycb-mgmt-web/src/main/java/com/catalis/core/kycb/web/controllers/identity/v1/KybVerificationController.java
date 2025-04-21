package com.catalis.core.kycb.web.controllers.identity.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.kyb.v1.KybVerificationService;
import com.catalis.core.kycb.interfaces.dtos.kyb.v1.KybVerificationDTO;
import com.catalis.core.kycb.interfaces.dtos.kyc.v1.KycVerificationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/identity/parties/{partyId}/kyb")
@Tag(name = "KYB Verification", description = "API for managing KYB verifications")
public class KybVerificationController {

    @Autowired
    private KybVerificationService kybVerificationService;

    @GetMapping
    @Operation(
            summary = "List KYB verifications for a party",
            description = "Retrieves all KYB verifications for the specified party ID with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved KYB verifications",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<PaginationResponse<KybVerificationDTO>> listKybVerifications(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @ModelAttribute FilterRequest<KybVerificationDTO> filterRequest
    ) {

        // Create a filter with the party ID
        KybVerificationDTO filter = new KybVerificationDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return kybVerificationService.findAll(filterRequest);
    }

    @GetMapping("/{verificationId}")
    @Operation(
            summary = "Get specific KYB verification",
            description = "Retrieves a specific KYB verification by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved KYB verification",
                            content = @Content(schema = @Schema(implementation = KybVerificationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "KYB verification not found"
                    )
            }
    )
    public Mono<KybVerificationDTO> getKybVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId
    ) {
        return kybVerificationService.getById(verificationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Start new KYB verification",
            description = "Creates a new KYB verification for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created KYB verification",
                            content = @Content(schema = @Schema(implementation = KybVerificationDTO.class))
                    )
            }
    )
    public Mono<KybVerificationDTO> createKybVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "KYB verification data", required = true)
            @RequestBody KybVerificationDTO kybVerificationDTO
    ) {
        kybVerificationDTO.setPartyId(partyId);
        return kybVerificationService.create(kybVerificationDTO);
    }

    @PatchMapping("/{verificationId}")
    @Operation(
            summary = "Update KYB verification",
            description = "Updates an existing KYB verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated KYB verification",
                            content = @Content(schema = @Schema(implementation = KybVerificationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "KYB verification not found"
                    )
            }
    )
    public Mono<KybVerificationDTO> updateKybVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "Updated KYB verification data", required = true)
            @RequestBody KybVerificationDTO kybVerificationDTO
    ) {
        kybVerificationDTO.setPartyId(partyId);
        return kybVerificationService.update(verificationId, kybVerificationDTO);
    }

    @PostMapping("/{verificationId}/complete")
    @Operation(
            summary = "Complete KYB verification",
            description = "Marks a KYB verification as complete",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully completed KYB verification",
                            content = @Content(schema = @Schema(implementation = KybVerificationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "KYB verification not found"
                    )
            }
    )
    public Mono<KybVerificationDTO> completeKybVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId
    ) {
        return kybVerificationService.complete(verificationId);
    }
}
