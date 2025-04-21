package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.aml.v1.AmlScreeningService;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
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
@RequestMapping("/api/v1/compliance/parties/{partyId}/aml-screenings")
@Tag(name = "AML Screening", description = "API for managing AML screenings")
public class AmlScreeningController {

    @Autowired
    private AmlScreeningService amlScreeningService;

    @GetMapping
    @Operation(
            summary = "List AML screenings",
            description = "Retrieves all AML screenings for the specified party ID with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved AML screenings",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Flux<AmlScreeningDTO> listAmlScreenings(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return amlScreeningService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Execute new AML screening",
            description = "Executes a new AML screening for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully executed AML screening",
                            content = @Content(schema = @Schema(implementation = AmlScreeningDTO.class))
                    )
            }
    )
    public Mono<AmlScreeningDTO> executeAmlScreening(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Screening type", required = true)
            @RequestParam String screeningType
    ) {
        return amlScreeningService.executeScreening(partyId, screeningType);
    }

    @GetMapping("/{screeningId}")
    @Operation(
            summary = "Get screening details",
            description = "Retrieves a specific AML screening by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved AML screening",
                            content = @Content(schema = @Schema(implementation = AmlScreeningDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AML screening not found"
                    )
            }
    )
    public Mono<AmlScreeningDTO> getAmlScreening(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId
    ) {
        return amlScreeningService.getById(screeningId);
    }

    @PatchMapping("/{screeningId}")
    @Operation(
            summary = "Update AML screening",
            description = "Updates an existing AML screening",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated AML screening",
                            content = @Content(schema = @Schema(implementation = AmlScreeningDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AML screening not found"
                    )
            }
    )
    public Mono<AmlScreeningDTO> updateAmlScreening(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "Updated AML screening data", required = true)
            @RequestBody AmlScreeningDTO amlScreeningDTO
    ) {
        amlScreeningDTO.setPartyId(partyId);
        return amlScreeningService.update(screeningId, amlScreeningDTO);
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest screening",
            description = "Retrieves the latest AML screening for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved latest AML screening",
                            content = @Content(schema = @Schema(implementation = AmlScreeningDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No AML screenings found for party"
                    )
            }
    )
    public Mono<AmlScreeningDTO> getLatestAmlScreening(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return amlScreeningService.getLatestByPartyId(partyId);
    }
}