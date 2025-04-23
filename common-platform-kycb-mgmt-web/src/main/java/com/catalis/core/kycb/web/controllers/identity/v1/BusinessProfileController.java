package com.catalis.core.kycb.web.controllers.identity.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.business.v1.BusinessProfileService;
import com.catalis.core.kycb.interfaces.dtos.business.v1.BusinessProfileDTO;
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
@RequestMapping("/api/v1/identity/parties/{partyId}/business-profile")
@Tag(name = "Business Profile", description = "API for managing business profiles")
public class BusinessProfileController {

    @Autowired
    private BusinessProfileService businessProfileService;

    @GetMapping("/{businessProfileId}")
    @Operation(
            summary = "Get business profile",
            description = "Retrieves a business profile by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business profile",
                            content = @Content(schema = @Schema(implementation = BusinessProfileDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business profile not found"
                    )
            }
    )
    public Mono<ResponseEntity<BusinessProfileDTO>> getBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the business profile", required = true)
            @PathVariable Long businessProfileId
    ) {
        return businessProfileService.getById(businessProfileId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Create business profile",
            description = "Creates a new business profile for a specific party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created business profile",
                            content = @Content(schema = @Schema(implementation = BusinessProfileDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<BusinessProfileDTO>> createBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Business profile data", required = true)
            @RequestBody BusinessProfileDTO businessProfileDTO
    ) {
        businessProfileDTO.setPartyId(partyId);
        return businessProfileService.create(businessProfileDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @PatchMapping("/{businessProfileId}")
    @Operation(
            summary = "Update business profile",
            description = "Updates a business profile by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated business profile",
                            content = @Content(schema = @Schema(implementation = BusinessProfileDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business profile not found"
                    )
            }
    )
    public Mono<ResponseEntity<BusinessProfileDTO>> updateBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the business profile", required = true)
            @PathVariable Long businessProfileId,
            @Parameter(description = "Updated business profile data", required = true)
            @RequestBody BusinessProfileDTO businessProfileDTO
    ) {
        businessProfileDTO.setPartyId(partyId);
        return businessProfileService.update(businessProfileId, businessProfileDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{businessProfileId}")
    @Operation(
            summary = "Delete business profile",
            description = "Deletes a business profile by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted business profile"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business profile not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the business profile", required = true)
            @PathVariable Long businessProfileId
    ) {
        return businessProfileService.delete(businessProfileId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }

    @GetMapping
    @Operation(
            summary = "List business profiles",
            description = "Retrieves all business profiles with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business profiles",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<BusinessProfileDTO>>> listBusinessProfiles(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<BusinessProfileDTO> filterRequest
    ) {
        // Set party ID filter
        BusinessProfileDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new BusinessProfileDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return businessProfileService.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }
}
