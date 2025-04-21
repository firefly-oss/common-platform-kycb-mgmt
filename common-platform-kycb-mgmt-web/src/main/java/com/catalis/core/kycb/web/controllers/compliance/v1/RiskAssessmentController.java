package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.risk.v1.RiskAssessmentService;
import com.catalis.core.kycb.interfaces.dtos.risk.v1.RiskAssessmentDTO;
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
@RequestMapping("/api/v1/compliance/parties/{partyId}/risk-assessments")
@Tag(name = "Risk Assessment", description = "API for managing risk assessments")
public class RiskAssessmentController {

    @Autowired
    private RiskAssessmentService riskAssessmentService;

    @GetMapping
    @Operation(
            summary = "List risk assessments",
            description = "Retrieves all risk assessments for the specified party ID with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved risk assessments",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Flux<RiskAssessmentDTO> listRiskAssessments(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return riskAssessmentService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Create new risk assessment",
            description = "Creates a new risk assessment for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created risk assessment",
                            content = @Content(schema = @Schema(implementation = RiskAssessmentDTO.class))
                    )
            }
    )
    public Mono<RiskAssessmentDTO> createRiskAssessment(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Risk assessment data", required = true)
            @RequestBody RiskAssessmentDTO riskAssessmentDTO
    ) {
        riskAssessmentDTO.setPartyId(partyId);
        return riskAssessmentService.create(riskAssessmentDTO);
    }

    @GetMapping("/{assessmentId}")
    @Operation(
            summary = "Get specific assessment",
            description = "Retrieves a specific risk assessment by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved risk assessment",
                            content = @Content(schema = @Schema(implementation = RiskAssessmentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Risk assessment not found"
                    )
            }
    )
    public Mono<RiskAssessmentDTO> getRiskAssessment(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the assessment", required = true)
            @PathVariable Long assessmentId
    ) {
        return riskAssessmentService.getById(assessmentId);
    }

    @PostMapping("/calculate")
    @Operation(
            summary = "Calculate risk score",
            description = "Calculates a risk score for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully calculated risk score",
                            content = @Content(schema = @Schema(implementation = RiskAssessmentDTO.class))
                    )
            }
    )
    public Mono<RiskAssessmentDTO> calculateRiskScore(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Assessment type", required = true)
            @RequestParam String assessmentType
    ) {
        return riskAssessmentService.calculateRiskScore(partyId, assessmentType);
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest assessment",
            description = "Retrieves the latest risk assessment for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved latest risk assessment",
                            content = @Content(schema = @Schema(implementation = RiskAssessmentDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No risk assessments found for party"
                    )
            }
    )
    public Mono<RiskAssessmentDTO> getLatestRiskAssessment(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return riskAssessmentService.getLatestByPartyId(partyId);
    }
}