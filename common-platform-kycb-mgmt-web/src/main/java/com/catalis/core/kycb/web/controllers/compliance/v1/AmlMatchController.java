package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.core.kycb.core.services.aml.v1.AmlMatchService;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlMatchDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
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
            description = "Retrieves all matches for the specified AML screening",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved AML matches",
                            content = @Content(schema = @Schema(implementation = AmlMatchDTO.class))
                    )
            }
    )
    public Flux<AmlMatchDTO> listAmlMatches(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId
    ) {
        return amlMatchService.findByAmlScreeningId(screeningId);
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
    public Mono<AmlMatchDTO> getAmlMatch(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "ID of the match", required = true)
            @PathVariable Long matchId
    ) {
        return amlMatchService.getById(matchId);
    }

    @PatchMapping("/{matchId}")
    @Operation(
            summary = "Update match resolution",
            description = "Updates the resolution status of an AML match",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated AML match resolution",
                            content = @Content(schema = @Schema(implementation = AmlMatchDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "AML match not found"
                    )
            }
    )
    public Mono<AmlMatchDTO> updateMatchResolution(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the screening", required = true)
            @PathVariable Long screeningId,
            @Parameter(description = "ID of the match", required = true)
            @PathVariable Long matchId,
            @Parameter(description = "Resolution status", required = true)
            @RequestParam String resolutionStatus,
            @Parameter(description = "Resolution notes")
            @RequestParam(required = false) String resolutionNotes,
            @Parameter(description = "Resolution agent", required = true)
            @RequestParam String resolutionAgent
    ) {
        return amlMatchService.updateResolution(matchId, resolutionStatus, resolutionNotes, resolutionAgent);
    }
}