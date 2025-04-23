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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

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
                            content = @Content(schema = @Schema(implementation = PaginationResponse.class))
                    )
            }
    )
    public Mono<ResponseEntity<PaginationResponse<RegulatoryReportingDTO>>> listRegulatoryReports(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Filter criteria")
            @ModelAttribute FilterRequest<RegulatoryReportingDTO> filterRequest
    ) {
        // Set compliance case ID filter
        RegulatoryReportingDTO filter = filterRequest.getFilters() != null ? filterRequest.getFilters() : new RegulatoryReportingDTO();
        filter.setComplianceCaseId(caseId);
        filterRequest.setFilters(filter);

        return regulatoryReportingService.findAll(filterRequest)
                .map(ResponseEntity::ok);
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
    public Mono<ResponseEntity<RegulatoryReportingDTO>> submitRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "Regulatory report data", required = true)
            @RequestBody RegulatoryReportingDTO regulatoryReportingDTO
    ) {
        regulatoryReportingDTO.setComplianceCaseId(caseId);
        return regulatoryReportingService.create(regulatoryReportingDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
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
    public Mono<ResponseEntity<RegulatoryReportingDTO>> getRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId
    ) {
        return regulatoryReportingService.getById(reportId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
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
    public Mono<ResponseEntity<RegulatoryReportingDTO>> updateRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId,
            @Parameter(description = "Updated regulatory report data", required = true)
            @RequestBody RegulatoryReportingDTO regulatoryReportingDTO
    ) {
        regulatoryReportingDTO.setComplianceCaseId(caseId);
        return regulatoryReportingService.update(reportId, regulatoryReportingDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{reportId}")
    @Operation(
            summary = "Delete regulatory report",
            description = "Deletes a regulatory report",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted regulatory report"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Regulatory report not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteRegulatoryReport(
            @Parameter(description = "ID of the compliance case", required = true)
            @PathVariable Long caseId,
            @Parameter(description = "ID of the regulatory report", required = true)
            @PathVariable Long reportId
    ) {
        return regulatoryReportingService.delete(reportId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
