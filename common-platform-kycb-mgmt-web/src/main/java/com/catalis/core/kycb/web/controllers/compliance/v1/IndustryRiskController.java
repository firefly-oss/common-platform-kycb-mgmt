package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.industry.v1.IndustryRiskService;
import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/compliance/industry-risks")
@Tag(name = "Industry Risks", description = "API for managing industry risk profiles")
public class IndustryRiskController {

    @Autowired
    private IndustryRiskService industryRiskService;

    @GetMapping
    @Operation(
            summary = "List industry risk profiles",
            description = "Retrieves all industry risk profiles with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<PaginationResponse<IndustryRiskDTO>> listIndustryRisks(
            @Parameter(description = "Filter request", required = false)
            @ModelAttribute FilterRequest<IndustryRiskDTO> filterRequest
    ) {
        return industryRiskService.findAll(filterRequest);
    }

    @GetMapping("/{activityCode}")
    @Operation(
            summary = "Get specific industry risk profile",
            description = "Retrieves a specific industry risk profile by its activity code",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profile",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Industry risk profile not found"
                    )
            }
    )
    public Mono<IndustryRiskDTO> getIndustryRisk(
            @Parameter(description = "Activity code", required = true)
            @PathVariable String activityCode
    ) {
        return industryRiskService.getByActivityCode(activityCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create industry risk profile",
            description = "Creates a new industry risk profile",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created industry risk profile",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Mono<IndustryRiskDTO> createIndustryRisk(
            @Parameter(description = "Industry risk profile data", required = true)
            @RequestBody IndustryRiskDTO industryRiskDTO
    ) {
        return industryRiskService.create(industryRiskDTO);
    }

    @PatchMapping("/{activityCode}")
    @Operation(
            summary = "Update industry risk profile",
            description = "Updates an existing industry risk profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated industry risk profile",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Industry risk profile not found"
                    )
            }
    )
    public Mono<IndustryRiskDTO> updateIndustryRisk(
            @Parameter(description = "Activity code", required = true)
            @PathVariable String activityCode,
            @Parameter(description = "Updated industry risk profile data", required = true)
            @RequestBody IndustryRiskDTO industryRiskDTO
    ) {
        return industryRiskService.updateByActivityCode(activityCode, industryRiskDTO);
    }

    @DeleteMapping("/{industryRiskId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete industry risk profile",
            description = "Deletes an industry risk profile",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted industry risk profile"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Industry risk profile not found"
                    )
            }
    )
    public Mono<Void> deleteIndustryRisk(
            @Parameter(description = "ID of the industry risk profile", required = true)
            @PathVariable Long industryRiskId
    ) {
        return industryRiskService.delete(industryRiskId);
    }

    @GetMapping("/risk-level/{riskLevel}")
    @Operation(
            summary = "Get industry risks by risk level",
            description = "Retrieves all industry risk profiles with a specific risk level",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getIndustryRisksByRiskLevel(
            @Parameter(description = "Risk level", required = true)
            @PathVariable String riskLevel
    ) {
        return industryRiskService.findByInherentRiskLevel(riskLevel);
    }

    @GetMapping("/risk-score-above/{threshold}")
    @Operation(
            summary = "Get industry risks by risk score",
            description = "Retrieves all industry risk profiles with a risk score above the specified threshold",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getIndustryRisksByRiskScore(
            @Parameter(description = "Risk score threshold", required = true)
            @PathVariable Integer threshold
    ) {
        return industryRiskService.findByRiskScoreAbove(threshold);
    }

    @GetMapping("/sepblac-high-risk")
    @Operation(
            summary = "Get SEPBLAC high-risk industries",
            description = "Retrieves all industry risk profiles that are high risk according to SEPBLAC",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getSepblacHighRiskIndustries() {
        return industryRiskService.findSepblacHighRisk();
    }

    @GetMapping("/eu-high-risk")
    @Operation(
            summary = "Get EU high-risk industries",
            description = "Retrieves all industry risk profiles that are high risk according to EU",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getEuHighRiskIndustries() {
        return industryRiskService.findEuHighRisk();
    }

    @GetMapping("/fatf-high-risk")
    @Operation(
            summary = "Get FATF high-risk industries",
            description = "Retrieves all industry risk profiles that are high risk according to FATF",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getFatfHighRiskIndustries() {
        return industryRiskService.findFatfHighRisk();
    }

    @GetMapping("/cash-intensive")
    @Operation(
            summary = "Get cash-intensive industries",
            description = "Retrieves all industry risk profiles that are cash intensive",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getCashIntensiveIndustries() {
        return industryRiskService.findCashIntensive();
    }

    @GetMapping("/complex-structures")
    @Operation(
            summary = "Get industries with complex structures",
            description = "Retrieves all industry risk profiles that involve complex structures",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getComplexStructureIndustries() {
        return industryRiskService.findComplexStructures();
    }

    @GetMapping("/requires-edd")
    @Operation(
            summary = "Get industries requiring EDD",
            description = "Retrieves all industry risk profiles that require enhanced due diligence",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getIndustriesRequiringEdd() {
        return industryRiskService.findRequiresEdd();
    }

    @GetMapping("/needs-reassessment")
    @Operation(
            summary = "Get industries needing reassessment",
            description = "Retrieves all industry risk profiles that need reassessment",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved industry risk profiles",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    )
            }
    )
    public Flux<IndustryRiskDTO> getIndustriesNeedingReassessment() {
        return industryRiskService.findNeedsReassessment();
    }

    @PostMapping("/{industryRiskId}/assess")
    @Operation(
            summary = "Assess industry risk",
            description = "Assesses an industry risk profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully assessed industry risk profile",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Industry risk profile not found"
                    )
            }
    )
    public Mono<IndustryRiskDTO> assessIndustryRisk(
            @Parameter(description = "ID of the industry risk profile", required = true)
            @PathVariable Long industryRiskId,
            @Parameter(description = "User assessing the risk", required = true)
            @RequestParam String assessedBy,
            @Parameter(description = "Risk score", required = true)
            @RequestParam Integer riskScore,
            @Parameter(description = "Risk level", required = true)
            @RequestParam String riskLevel,
            @Parameter(description = "Risk factors")
            @RequestParam(required = false) String riskFactors,
            @Parameter(description = "Mitigating factors")
            @RequestParam(required = false) String mitigatingFactors
    ) {
        return industryRiskService.assessRisk(industryRiskId, assessedBy, riskScore, riskLevel, riskFactors, mitigatingFactors);
    }

    @PostMapping("/{industryRiskId}/schedule")
    @Operation(
            summary = "Schedule next assessment",
            description = "Schedules the next assessment for an industry risk profile",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully scheduled next assessment",
                            content = @Content(schema = @Schema(implementation = IndustryRiskDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Industry risk profile not found"
                    )
            }
    )
    public Mono<IndustryRiskDTO> scheduleNextAssessment(
            @Parameter(description = "ID of the industry risk profile", required = true)
            @PathVariable Long industryRiskId,
            @Parameter(description = "Next assessment date", required = true)
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime nextAssessmentDate
    ) {
        return industryRiskService.scheduleNextAssessment(industryRiskId, nextAssessmentDate);
    }
}
