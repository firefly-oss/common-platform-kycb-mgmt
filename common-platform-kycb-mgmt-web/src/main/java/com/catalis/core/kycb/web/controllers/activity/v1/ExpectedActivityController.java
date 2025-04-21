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
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/activity/parties/{partyId}/expected-activities")
@Tag(name = "Expected Activity", description = "API for managing expected activities")
public class ExpectedActivityController {

    @Autowired
    private ExpectedActivityService expectedActivityService;

    @GetMapping
    @Operation(
            summary = "List expected activities",
            description = "Retrieves all expected activities for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved expected activities",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Flux<ExpectedActivityDTO> listExpectedActivities(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return expectedActivityService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
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
    public Mono<ExpectedActivityDTO> addExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Expected activity data", required = true)
            @RequestBody ExpectedActivityDTO expectedActivityDTO
    ) {
        expectedActivityDTO.setPartyId(partyId);
        return expectedActivityService.create(expectedActivityDTO);
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
    public Mono<ExpectedActivityDTO> getExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return expectedActivityService.getById(activityId);
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
    public Mono<ExpectedActivityDTO> updateExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId,
            @Parameter(description = "Updated expected activity data", required = true)
            @RequestBody ExpectedActivityDTO expectedActivityDTO
    ) {
        expectedActivityDTO.setPartyId(partyId);
        return expectedActivityService.update(activityId, expectedActivityDTO);
    }

    @DeleteMapping("/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
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
    public Mono<Void> deleteExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return expectedActivityService.delete(activityId);
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest expected activity",
            description = "Retrieves the latest expected activity for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved latest expected activity",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No expected activities found for party"
                    )
            }
    )
    public Mono<ExpectedActivityDTO> getLatestExpectedActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return expectedActivityService.getLatestByPartyId(partyId);
    }

    @GetMapping("/type/{activityTypeCode}")
    @Operation(
            summary = "Get activities by type",
            description = "Retrieves all expected activities of a specific type for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved expected activities",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Flux<ExpectedActivityDTO> getActivitiesByType(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Activity type code", required = true)
            @PathVariable String activityTypeCode
    ) {
        return expectedActivityService.findByActivityTypeCode(activityTypeCode)
                .filter(activity -> activity.getPartyId().equals(partyId));
    }

    @GetMapping("/high-value")
    @Operation(
            summary = "Get high-value activities",
            description = "Retrieves all high-value expected activities for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved high-value activities",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Flux<ExpectedActivityDTO> getHighValueActivities(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return expectedActivityService.findHighValueActivities()
                .filter(activity -> activity.getPartyId().equals(partyId));
    }

    @GetMapping("/cash-intensive")
    @Operation(
            summary = "Get cash-intensive activities",
            description = "Retrieves all cash-intensive expected activities for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved cash-intensive activities",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Flux<ExpectedActivityDTO> getCashIntensiveActivities(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return expectedActivityService.findCashIntensiveActivities()
                .filter(activity -> activity.getPartyId().equals(partyId));
    }

    @GetMapping("/tax-haven")
    @Operation(
            summary = "Get tax haven activities",
            description = "Retrieves all expected activities with tax haven transactions for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved tax haven activities",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Flux<ExpectedActivityDTO> getTaxHavenActivities(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return expectedActivityService.findTaxHavenActivities()
                .filter(activity -> activity.getPartyId().equals(partyId));
    }

    @GetMapping("/monthly-volume-above/{threshold}")
    @Operation(
            summary = "Get activities by monthly volume",
            description = "Retrieves all expected activities with monthly volume above the specified threshold for the party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved expected activities",
                            content = @Content(schema = @Schema(implementation = ExpectedActivityDTO.class))
                    )
            }
    )
    public Flux<ExpectedActivityDTO> getActivitiesByMonthlyVolume(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Monthly volume threshold", required = true)
            @PathVariable BigDecimal threshold
    ) {
        return expectedActivityService.findByMonthlyVolumeAbove(threshold)
                .filter(activity -> activity.getPartyId().equals(partyId));
    }
}