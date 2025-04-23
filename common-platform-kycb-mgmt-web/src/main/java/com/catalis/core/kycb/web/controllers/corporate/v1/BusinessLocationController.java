package com.catalis.core.kycb.web.controllers.corporate.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.location.v1.BusinessLocationService;
import com.catalis.core.kycb.interfaces.dtos.location.v1.BusinessLocationDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/corporate/parties/{partyId}/locations")
@Tag(name = "Business Locations", description = "API for managing business locations")
public class BusinessLocationController {

    @Autowired
    private BusinessLocationService businessLocationService;

    @GetMapping
    @Operation(
            summary = "List business locations",
            description = "Retrieves all business locations with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business locations",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<BusinessLocationDTO>>> listBusinessLocations(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @ParameterObject
            @ModelAttribute FilterRequest<BusinessLocationDTO> filterRequest
    ) {
        // Set party ID filter
        BusinessLocationDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new BusinessLocationDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return businessLocationService.findAll(filterRequest)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(
            summary = "Add business location",
            description = "Adds a new business location to the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added business location",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<BusinessLocationDTO>> addBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Business location data", required = true)
            @RequestBody BusinessLocationDTO businessLocationDTO
    ) {
        businessLocationDTO.setPartyId(partyId);
        return businessLocationService.create(businessLocationDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{locationId}")
    @Operation(
            summary = "Get specific location",
            description = "Retrieves a specific business location by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business location",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business location not found"
                    )
            }
    )
    public Mono<ResponseEntity<BusinessLocationDTO>> getBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId
    ) {
        return businessLocationService.getById(locationId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{locationId}")
    @Operation(
            summary = "Update business location",
            description = "Updates an existing business location",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated business location",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business location not found"
                    )
            }
    )
    public Mono<ResponseEntity<BusinessLocationDTO>> updateBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId,
            @Parameter(description = "Updated business location data", required = true)
            @RequestBody BusinessLocationDTO businessLocationDTO
    ) {
        businessLocationDTO.setPartyId(partyId);
        return businessLocationService.update(locationId, businessLocationDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{locationId}")
    @Operation(
            summary = "Delete business location",
            description = "Deletes a business location",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted business location"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business location not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId
    ) {
        return businessLocationService.delete(locationId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
