package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.aml.v1.AmlMatchService;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlMatchDTO;
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
@RequestMapping("/api/v1/compliance/parties/{partyId}/aml-screenings/{screeningId}/matches")
@Tag(name = "AML Matches", description = "API for managing AML screening matches")
public class AmlMatchController {

    @Autowired
    private AmlMatchService amlMatchService;

    @GetMapping
    @Operation(
            summary = "List matches",
            description = "Retrieves all matches for the specified AML screening with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved AML matches",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<AmlMatchDTO>>> listAmlMatches(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<AmlMatchDTO> filterRequest
    ) {
        // Set screening ID filter
        AmlMatchDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new AmlMatchDTO();
        filter.setAmlScreeningId(screeningId);
        filterRequest.setFilters(filter);

        return amlMatchService.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(
            summary = "Create AML match",
            description = "Creates a new AML match for the specified screening",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created AML match",
                            content = @Content(schema = @Schema(implementation = AmlMatchDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<AmlMatchDTO>> createAmlMatch(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "AML match data", required = true)
            @RequestBody AmlMatchDTO amlMatchDTO
    ) {
        amlMatchDTO.setAmlScreeningId(screeningId);
        return amlMatchService.create(amlMatchDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{matchId}")
    @Operation(
            summary = "Get specific match",
            description = "Retrieves a specific AML match by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved AML match",
                            content = @Content(schema = @Schema(implementation = AmlMatchDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AML match not found"
                    )
            }
    )
    public Mono<ResponseEntity<AmlMatchDTO>> getAmlMatch(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "ID of the match", required = true)
            @PathVariable Long matchId
    ) {
        return amlMatchService.getById(matchId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{matchId}")
    @Operation(
            summary = "Update AML match",
            description = "Updates an existing AML match",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated AML match",
                            content = @Content(schema = @Schema(implementation = AmlMatchDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AML match not found"
                    )
            }
    )
    public Mono<ResponseEntity<AmlMatchDTO>> updateAmlMatch(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "ID of the match", required = true)
            @PathVariable Long matchId,
            @Parameter(description = "Updated AML match data", required = true)
            @RequestBody AmlMatchDTO amlMatchDTO
    ) {
        amlMatchDTO.setAmlScreeningId(screeningId);
        return amlMatchService.update(matchId, amlMatchDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{matchId}")
    @Operation(
            summary = "Delete AML match",
            description = "Deletes an AML match",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted AML match"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AML match not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteAmlMatch(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "ID of the match", required = true)
            @PathVariable Long matchId
    ) {
        return amlMatchService.delete(matchId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
