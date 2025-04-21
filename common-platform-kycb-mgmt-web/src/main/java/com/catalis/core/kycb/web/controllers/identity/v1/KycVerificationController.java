package com.catalis.core.kycb.web.controllers.identity.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.kyc.v1.KycVerificationService;
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
@RequestMapping("/api/v1/identity/parties/{partyId}/kyc")
@Tag(name = "KYC Verification", description = "API for managing KYC verifications")
public class KycVerificationController {

    @Autowired
    private KycVerificationService kycVerificationService;

    @GetMapping
    @Operation(
            summary = "List KYC verifications for a party",
            description = "Retrieves all KYC verifications for the specified party ID with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved KYC verifications",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<PaginationResponse<KycVerificationDTO>> listKycVerifications(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @ModelAttribute FilterRequest<KycVerificationDTO> filterRequest
    ) {
        // Create a filter with the party ID
        KycVerificationDTO filter = new KycVerificationDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return kycVerificationService.findAll(filterRequest);
    }

    @GetMapping("/{verificationId}")
    @Operation(
            summary = "Get specific KYC verification",
            description = "Retrieves a specific KYC verification by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved KYC verification",
                            content = @Content(schema = @Schema(implementation = KycVerificationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "KYC verification not found"
                    )
            }
    )
    public Mono<KycVerificationDTO> getKycVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId
    ) {
        return kycVerificationService.getById(verificationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Start new KYC verification",
            description = "Creates a new KYC verification for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created KYC verification",
                            content = @Content(schema = @Schema(implementation = KycVerificationDTO.class))
                    )
            }
    )
    public Mono<KycVerificationDTO> createKycVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "KYC verification data", required = true)
            @RequestBody KycVerificationDTO kycVerificationDTO
    ) {
        kycVerificationDTO.setPartyId(partyId);
        return kycVerificationService.create(kycVerificationDTO);
    }

    @PatchMapping("/{verificationId}")
    @Operation(
            summary = "Update KYC verification",
            description = "Updates an existing KYC verification",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated KYC verification",
                            content = @Content(schema = @Schema(implementation = KycVerificationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "KYC verification not found"
                    )
            }
    )
    public Mono<KycVerificationDTO> updateKycVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId,
            @Parameter(description = "Updated KYC verification data", required = true)
            @RequestBody KycVerificationDTO kycVerificationDTO
    ) {
        kycVerificationDTO.setPartyId(partyId);
        return kycVerificationService.update(verificationId, kycVerificationDTO);
    }

    @PostMapping("/{verificationId}/complete")
    @Operation(
            summary = "Complete KYC verification",
            description = "Marks a KYC verification as complete",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully completed KYC verification",
                            content = @Content(schema = @Schema(implementation = KycVerificationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "KYC verification not found"
                    )
            }
    )
    public Mono<KycVerificationDTO> completeKycVerification(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the verification", required = true)
            @PathVariable Long verificationId
    ) {
        return kycVerificationService.complete(verificationId);
    }
}
