package com.catalis.core.kycb.web.controllers.corporate.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.economic.v1.EconomicActivityService;
import com.catalis.core.kycb.interfaces.dtos.economic.v1.EconomicActivityDTO;
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
@RequestMapping("/api/v1/corporate/parties/{partyId}/activities")
@Tag(name = "Economic Activities", description = "API for managing economic activities of legal persons")
public class EconomicActivityController {

    @Autowired
    private EconomicActivityService economicActivityService;

    @GetMapping
    @Operation(
            summary = "List economic activities",
            description = "Retrieves all economic activities for the specified legal person",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved economic activities",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    )
            }
    )
    public Flux<EconomicActivityDTO> listEconomicActivities(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return economicActivityService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add economic activity",
            description = "Adds a new economic activity to the specified legal person",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added economic activity",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    )
            }
    )
    public Mono<EconomicActivityDTO> addEconomicActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Economic activity data", required = true)
            @RequestBody EconomicActivityDTO economicActivityDTO
    ) {
        economicActivityDTO.setPartyId(partyId);
        return economicActivityService.create(economicActivityDTO);
    }

    @GetMapping("/{activityId}")
    @Operation(
            summary = "Get specific activity",
            description = "Retrieves a specific economic activity by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved economic activity",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Economic activity not found"
                    )
            }
    )
    public Mono<EconomicActivityDTO> getEconomicActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return economicActivityService.getById(activityId);
    }

    @PatchMapping("/{activityId}")
    @Operation(
            summary = "Update economic activity",
            description = "Updates an existing economic activity",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated economic activity",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Economic activity not found"
                    )
            }
    )
    public Mono<EconomicActivityDTO> updateEconomicActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId,
            @Parameter(description = "Updated economic activity data", required = true)
            @RequestBody EconomicActivityDTO economicActivityDTO
    ) {
        economicActivityDTO.setPartyId(partyId);
        return economicActivityService.update(activityId, economicActivityDTO);
    }

    @DeleteMapping("/{activityId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete economic activity",
            description = "Deletes an economic activity",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted economic activity"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Economic activity not found"
                    )
            }
    )
    public Mono<Void> deleteEconomicActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return economicActivityService.delete(activityId);
    }

    @PostMapping("/{activityId}/set-primary")
    @Operation(
            summary = "Set primary activity",
            description = "Sets an economic activity as the primary activity for the legal person",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully set primary activity",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Economic activity not found"
                    )
            }
    )
    public Mono<EconomicActivityDTO> setPrimaryActivity(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the activity", required = true)
            @PathVariable Long activityId
    ) {
        return economicActivityService.setPrimaryActivity(activityId);
    }

    @GetMapping("/code/{activityCode}")
    @Operation(
            summary = "Get activities by code",
            description = "Retrieves all economic activities with the specified activity code",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved economic activities",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    )
            }
    )
    public Flux<EconomicActivityDTO> getActivitiesByCode(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Activity code", required = true)
            @PathVariable String activityCode
    ) {
        return economicActivityService.findByActivityCode(activityCode)
                .filter(activity -> activity.getPartyId().equals(partyId));
    }

    @GetMapping("/risk/{riskLevel}")
    @Operation(
            summary = "Get activities by risk level",
            description = "Retrieves all economic activities with the specified risk level",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved economic activities",
                            content = @Content(schema = @Schema(implementation = EconomicActivityDTO.class))
                    )
            }
    )
    public Flux<EconomicActivityDTO> getActivitiesByRiskLevel(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Risk level", required = true)
            @PathVariable String riskLevel
    ) {
        return economicActivityService.findByRiskLevel(riskLevel)
                .filter(activity -> activity.getPartyId().equals(partyId));
    }
}
