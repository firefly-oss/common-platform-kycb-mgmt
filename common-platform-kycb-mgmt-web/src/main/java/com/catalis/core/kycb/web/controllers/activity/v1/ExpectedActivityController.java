package com.catalis.core.kycb.web.controllers.activity.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.expected.v1.ExpectedActivityService;
import com.catalis.core.kycb.interfaces.dtos.expected.v1.ExpectedActivityDTO;
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
@RequestMapping("/api/v1/activity/parties/{partyId}/expected-activities")
@Tag(name = "Expected Activity", description = "API for managing expected activities")
public class ExpectedActivityController {

    @Autowired
    private ExpectedActivityService expectedActivityService;

    @GetMapping
    @Operation(
            summary = "List expected activities",
            description = "Retrieves all expected activities for the specified party with filtering capabilities",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved expected activities",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<ExpectedActivityDTO>>> listExpectedActivities(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<ExpectedActivityDTO> filterRequest
    ) {
        // Set party ID filter
        ExpectedActivityDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new ExpectedActivityDTO();
        filter.setPartyId(partyId);
        filterRequest.setFilters(filter);

        return expectedActivityService.findAll(filterRequest)
                .map(ResponseEntity::ok);
    }

    @PostMapping
    @Operation(
            summary = "Add expected activity",
            description = "Adds a new expected activity for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added expected activity",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<ExpectedActivityDTO>> addExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Expected activity data", required = true)
            @RequestBody ExpectedActivityDTO expectedActivityDTO
    ) {
        expectedActivityDTO.setPartyId(partyId);
        return expectedActivityService.create(expectedActivityDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @GetMapping("/{activityId}")
    @Operation(
            summary = "Get specific expected activity",
            description = "Retrieves a specific expected activity by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved expected activity",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Expected activity not found"
                    )
            }
    )
    public Mono<ResponseEntity<ExpectedActivityDTO>> getExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return expectedActivityService.getById(activityId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{activityId}")
    @Operation(
            summary = "Update expected activity",
            description = "Updates an existing expected activity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated expected activity",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Expected activity not found"
                    )
            }
    )
    public Mono<ResponseEntity<ExpectedActivityDTO>> updateExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId,
            @Parameter(description = "Updated expected activity data", required = true)
            @RequestBody ExpectedActivityDTO expectedActivityDTO
    ) {
        expectedActivityDTO.setPartyId(partyId);
        return expectedActivityService.update(activityId, expectedActivityDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{activityId}")
    @Operation(
            summary = "Delete expected activity",
            description = "Deletes an expected activity",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted expected activity"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Expected activity not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return expectedActivityService.delete(activityId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}