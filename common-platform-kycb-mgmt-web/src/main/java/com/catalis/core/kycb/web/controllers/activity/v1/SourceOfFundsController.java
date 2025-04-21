package com.catalis.core.kycb.web.controllers.activity.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.source.v1.SourceOfFundsService;
import com.catalis.core.kycb.interfaces.dtos.source.v1.SourceOfFundsDTO;
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
@RequestMapping("/api/v1/activity/parties/{partyId}/sources-of-funds")
@Tag(name = "Source of Funds", description = "API for managing sources of funds")
public class SourceOfFundsController {

    @Autowired
    private SourceOfFundsService sourceOfFundsService;

    @GetMapping
    @Operation(
            summary = "List sources of funds",
            description = "Retrieves all sources of funds for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved sources of funds",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    )
            }
    )
    public Flux<SourceOfFundsDTO> listSourcesOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return sourceOfFundsService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add source of funds",
            description = "Adds a new source of funds for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added source of funds",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    )
            }
    )
    public Mono<SourceOfFundsDTO> addSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Source of funds data", required = true)
            @RequestBody SourceOfFundsDTO sourceOfFundsDTO
    ) {
        sourceOfFundsDTO.setPartyId(partyId);
        return sourceOfFundsService.create(sourceOfFundsDTO);
    }

    @GetMapping("/{sourceId}")
    @Operation(
            summary = "Get specific source",
            description = "Retrieves a specific source of funds by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved source of funds",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Source of funds not found"
                    )
            }
    )
    public Mono<SourceOfFundsDTO> getSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId
    ) {
        return sourceOfFundsService.getById(sourceId);
    }

    @PatchMapping("/{sourceId}")
    @Operation(
            summary = "Update source of funds",
            description = "Updates an existing source of funds",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated source of funds",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Source of funds not found"
                    )
            }
    )
    public Mono<SourceOfFundsDTO> updateSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId,
            @Parameter(description = "Updated source of funds data", required = true)
            @RequestBody SourceOfFundsDTO sourceOfFundsDTO
    ) {
        sourceOfFundsDTO.setPartyId(partyId);
        return sourceOfFundsService.update(sourceId, sourceOfFundsDTO);
    }

    @DeleteMapping("/{sourceId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete source of funds",
            description = "Deletes a source of funds",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted source of funds"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Source of funds not found"
                    )
            }
    )
    public Mono<Void> deleteSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId
    ) {
        return sourceOfFundsService.delete(sourceId);
    }

    @PostMapping("/{sourceId}/verify")
    @Operation(
            summary = "Verify source of funds",
            description = "Marks a source of funds as verified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully verified source of funds",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Source of funds not found"
                    )
            }
    )
    public Mono<SourceOfFundsDTO> verifySourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId,
            @Parameter(description = "Verification notes")
            @RequestParam(required = false) String verificationNotes
    ) {
        return sourceOfFundsService.verifySource(sourceId, verificationNotes);
    }

    @PostMapping("/{sourceId}/set-primary")
    @Operation(
            summary = "Set primary source",
            description = "Sets a source of funds as the primary source for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully set primary source",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Source of funds not found"
                    )
            }
    )
    public Mono<SourceOfFundsDTO> setPrimarySource(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId
    ) {
        return sourceOfFundsService.setPrimarySource(sourceId);
    }

    @GetMapping("/type/{sourceType}")
    @Operation(
            summary = "Get sources by type",
            description = "Retrieves all sources of funds of a specific type for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved sources of funds",
                            content = @Content(schema = @Schema(implementation = SourceOfFundsDTO.class))
                    )
            }
    )
    public Flux<SourceOfFundsDTO> getSourcesByType(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Type of source", required = true)
            @PathVariable String sourceType
    ) {
        return sourceOfFundsService.findBySourceType(sourceType)
                .filter(source -> source.getPartyId().equals(partyId));
    }
}