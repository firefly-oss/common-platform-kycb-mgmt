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
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/corporate/parties/{partyId}/locations")
@Tag(name = "Business Locations", description = "API for managing business locations")
public class BusinessLocationController {

    @Autowired
    private BusinessLocationService businessLocationService;

    @GetMapping
    @Operation(
            summary = "List business locations",
            description = "Retrieves all business locations for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business locations",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    )
            }
    )
    public Flux<BusinessLocationDTO> listBusinessLocations(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return businessLocationService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public Mono<BusinessLocationDTO> addBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Business location data", required = true)
            @RequestBody BusinessLocationDTO businessLocationDTO
    ) {
        businessLocationDTO.setPartyId(partyId);
        return businessLocationService.create(businessLocationDTO);
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
    public Mono<BusinessLocationDTO> getBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId
    ) {
        return businessLocationService.getById(locationId);
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
    public Mono<BusinessLocationDTO> updateBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId,
            @Parameter(description = "Updated business location data", required = true)
            @RequestBody BusinessLocationDTO businessLocationDTO
    ) {
        businessLocationDTO.setPartyId(partyId);
        return businessLocationService.update(locationId, businessLocationDTO);
    }

    @DeleteMapping("/{locationId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    public Mono<Void> deleteBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId
    ) {
        return businessLocationService.delete(locationId);
    }

    @GetMapping("/primary")
    @Operation(
            summary = "Get primary location",
            description = "Retrieves the primary business location for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved primary business location",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Primary business location not found"
                    )
            }
    )
    public Mono<BusinessLocationDTO> getPrimaryLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return businessLocationService.getPrimaryLocationByPartyId(partyId);
    }

    @PostMapping("/{locationId}/set-primary")
    @Operation(
            summary = "Set primary location",
            description = "Sets a business location as the primary location for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully set primary location",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business location not found"
                    )
            }
    )
    public Mono<BusinessLocationDTO> setPrimaryLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId
    ) {
        return businessLocationService.setPrimaryLocation(locationId);
    }

    @PostMapping("/{locationId}/verify")
    @Operation(
            summary = "Verify business location",
            description = "Marks a business location as verified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully verified business location",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Business location not found"
                    )
            }
    )
    public Mono<BusinessLocationDTO> verifyBusinessLocation(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the location", required = true)
            @PathVariable Long locationId,
            @Parameter(description = "Verification method", required = true)
            @RequestParam String verificationMethod
    ) {
        return businessLocationService.verifyLocation(locationId, verificationMethod);
    }

    @GetMapping("/type/{locationTypeCode}")
    @Operation(
            summary = "Get locations by type",
            description = "Retrieves all business locations of a specific type for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business locations",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    )
            }
    )
    public Flux<BusinessLocationDTO> getLocationsByType(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Location type code", required = true)
            @PathVariable String locationTypeCode
    ) {
        return businessLocationService.findByLocationTypeCode(locationTypeCode)
                .filter(location -> location.getPartyId().equals(partyId));
    }

    @GetMapping("/country/{countryIsoCode}")
    @Operation(
            summary = "Get locations by country",
            description = "Retrieves all business locations in a specific country for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business locations",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    )
            }
    )
    public Flux<BusinessLocationDTO> getLocationsByCountry(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Country ISO code", required = true)
            @PathVariable String countryIsoCode
    ) {
        return businessLocationService.findByCountry(countryIsoCode)
                .filter(location -> location.getPartyId().equals(partyId));
    }

    @GetMapping("/city/{city}")
    @Operation(
            summary = "Get locations by city",
            description = "Retrieves all business locations in a specific city for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved business locations",
                            content = @Content(schema = @Schema(implementation = BusinessLocationDTO.class))
                    )
            }
    )
    public Flux<BusinessLocationDTO> getLocationsByCity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "City", required = true)
            @PathVariable String city
    ) {
        return businessLocationService.findByCity(city)
                .filter(location -> location.getPartyId().equals(partyId));
    }
}
