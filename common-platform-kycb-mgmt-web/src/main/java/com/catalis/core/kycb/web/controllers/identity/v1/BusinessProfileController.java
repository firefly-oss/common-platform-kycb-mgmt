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
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/identity/parties/{partyId}/business-profile")
@Tag(name = "Business Profile", description = "API for managing business profiles")
public class BusinessProfileController {

    @Autowired
    private BusinessProfileService businessProfileService;

    @GetMapping
    @Operation(
            summary = "Get business profile",
            description = "Retrieves the business profile for a specific party",
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
    public Mono<BusinessProfileDTO> getBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return businessProfileService.getLatestByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public Mono<BusinessProfileDTO> createBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Business profile data", required = true)
            @RequestBody BusinessProfileDTO businessProfileDTO
    ) {
        businessProfileDTO.setPartyId(partyId);
        return businessProfileService.create(businessProfileDTO);
    }

    @PatchMapping
    @Operation(
            summary = "Update business profile",
            description = "Updates the business profile for a specific party",
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
    public Mono<BusinessProfileDTO> updateBusinessProfile(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Updated business profile data", required = true)
            @RequestBody BusinessProfileDTO businessProfileDTO
    ) {
        return businessProfileService.getLatestByPartyId(partyId)
                .flatMap(existingProfile -> {
                    businessProfileDTO.setBusinessProfileId(existingProfile.getBusinessProfileId());
                    businessProfileDTO.setPartyId(partyId);
                    return businessProfileService.update(existingProfile.getBusinessProfileId(), businessProfileDTO);
                });
    }

    @GetMapping("/history")
    @Operation(
            summary = "Get business profile history",
            description = "Retrieves all business profiles for a specific party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business profile history",
                            content = @Content(schema = @Schema(implementation = BusinessProfileDTO.class))
                    )
            }
    )
    public Flux<BusinessProfileDTO> getBusinessProfileHistory(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return businessProfileService.findByPartyId(partyId);
    }

    @GetMapping("/all")
    @Operation(
            summary = "List all business profiles",
            description = "Retrieves all business profiles with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business profiles",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<PaginationResponse<BusinessProfileDTO>> listBusinessProfiles(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<BusinessProfileDTO> filterRequest
    ) {
        // Create a filter with the party ID
        BusinessProfileDTO filter = new BusinessProfileDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return businessProfileService.findAll(filterRequest);
    }
}
