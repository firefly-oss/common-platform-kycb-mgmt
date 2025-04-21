package com.catalis.core.kycb.web.controllers.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.regulatory.v1.RegulatoryReportingService;
import com.catalis.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
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
@RequestMapping("/api/v1/compliance/cases/{caseId}/reports")
@Tag(name = "Regulatory Reporting", description = "API for managing regulatory reports")
public class RegulatoryReportingController {

    @Autowired
    private RegulatoryReportingService regulatoryReportingService;

    @GetMapping
    @Operation(
            summary = "List regulatory reports",
            description = "Retrieves all regulatory reports for a specific compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved regulatory reports",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    )
            }
    )
    public Flux<RegulatoryReportingDTO> listRegulatoryReports(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId
    ) {
        return regulatoryReportingService.findByComplianceCaseId(caseId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Submit regulatory report",
            description = "Creates a new regulatory report for a specific compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created regulatory report",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> submitRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Regulatory report data", required = true)
            @RequestBody RegulatoryReportingDTO regulatoryReportingDTO
    ) {
        regulatoryReportingDTO.setComplianceCaseId(caseId);
        return regulatoryReportingService.create(regulatoryReportingDTO);
    }

    @GetMapping("/{reportId}")
    @Operation(
            summary = "Get specific report",
            description = "Retrieves a specific regulatory report by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved regulatory report",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Regulatory report not found"
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> getRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId
    ) {
        return regulatoryReportingService.getById(reportId);
    }

    @PatchMapping("/{reportId}")
    @Operation(
            summary = "Update regulatory report",
            description = "Updates an existing regulatory report",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated regulatory report",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Regulatory report not found"
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> updateRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId,
            @Parameter(description = "Updated regulatory report data", required = true)
            @RequestBody RegulatoryReportingDTO regulatoryReportingDTO
    ) {
        regulatoryReportingDTO.setComplianceCaseId(caseId);
        return regulatoryReportingService.update(reportId, regulatoryReportingDTO);
    }

    @PostMapping("/{reportId}/submit")
    @Operation(
            summary = "Submit report to authority",
            description = "Submits a regulatory report to the regulatory authority",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully submitted regulatory report",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Regulatory report not found"
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> submitReportToAuthority(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId,
            @Parameter(description = "User submitting the report", required = true)
            @RequestParam String submittedBy,
            @Parameter(description = "Submission notes")
            @RequestParam(required = false) String submissionNotes
    ) {
        return regulatoryReportingService.submitReport(reportId, submittedBy, submissionNotes);
    }

    @PostMapping("/{reportId}/acknowledge")
    @Operation(
            summary = "Acknowledge report",
            description = "Marks a regulatory report as acknowledged by the regulatory authority",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully acknowledged regulatory report",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Regulatory report not found"
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> acknowledgeReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId,
            @Parameter(description = "User acknowledging the report", required = true)
            @RequestParam String acknowledgedBy,
            @Parameter(description = "Acknowledgment notes")
            @RequestParam(required = false) String acknowledgmentNotes
    ) {
        return regulatoryReportingService.approveReport(reportId, acknowledgedBy, acknowledgmentNotes);
    }

    @PostMapping("/{reportId}/supplement")
    @Operation(
            summary = "Supplement report",
            description = "Marks a regulatory report as requiring supplementary information",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully marked regulatory report for supplementation",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Regulatory report not found"
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> supplementReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId,
            @Parameter(description = "User requesting supplementation", required = true)
            @RequestParam String requestedBy,
            @Parameter(description = "Reason for supplementation", required = true)
            @RequestParam String supplementationReason
    ) {
        return regulatoryReportingService.rejectReport(reportId, requestedBy, supplementationReason);
    }

    @GetMapping("/status/{status}")
    @Operation(
            summary = "Get reports by status",
            description = "Retrieves all regulatory reports with a specific status for the compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved regulatory reports",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    )
            }
    )
    public Flux<RegulatoryReportingDTO> getReportsByStatus(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Status of the regulatory reports", required = true)
            @PathVariable String status
    ) {
        return regulatoryReportingService.findByComplianceCaseIdAndReportStatus(caseId, status);
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest report",
            description = "Retrieves the latest regulatory report for the compliance case",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved latest regulatory report",
                            content = @Content(schema = @Schema(implementation = RegulatoryReportingDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No regulatory reports found for compliance case"
                    )
            }
    )
    public Mono<RegulatoryReportingDTO> getLatestReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId
    ) {
        return regulatoryReportingService.getLatestByComplianceCaseId(caseId);
    }

    @GetMapping("/all")
    @Operation(
            summary = "List all regulatory reports",
            description = "Retrieves all regulatory reports with optional filtering",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved regulatory reports",
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<PaginationResponse<RegulatoryReportingDTO>> listAllRegulatoryReports(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<RegulatoryReportingDTO> filterRequest
    ) {
        // Create a filter with the compliance case ID
        RegulatoryReportingDTO filter = new RegulatoryReportingDTO();
        filter.setComplianceCaseId(caseId);
        filterRequest.setFilters(filter);

        return regulatoryReportingService.findAll(filterRequest);
    }
}
