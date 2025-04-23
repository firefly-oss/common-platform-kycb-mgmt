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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<SourceOfFundsDTO>>> listSourcesOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<SourceOfFundsDTO> filterRequest
    ) {
        // Set party ID filter
        SourceOfFundsDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new SourceOfFundsDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return sourceOfFundsService.findAll(filterRequest)
                .map(ResponseEntity::ok);
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
    public Mono<ResponseEntity<SourceOfFundsDTO>> addSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Source of funds data", required = true)
            @RequestBody SourceOfFundsDTO sourceOfFundsDTO
    ) {
        sourceOfFundsDTO.setPartyId(partyId);
        return sourceOfFundsService.create(sourceOfFundsDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
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
    public Mono<ResponseEntity<SourceOfFundsDTO>> getSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId
    ) {
        return sourceOfFundsService.getById(sourceId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<SourceOfFundsDTO>> updateSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId,
            @Parameter(description = "Updated source of funds data", required = true)
            @RequestBody SourceOfFundsDTO sourceOfFundsDTO
    ) {
        sourceOfFundsDTO.setPartyId(partyId);
        return sourceOfFundsService.update(sourceId, sourceOfFundsDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<Void>> deleteSourceOfFunds(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the source", required = true)
            @PathVariable Long sourceId
    ) {
        return sourceOfFundsService.delete(sourceId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
