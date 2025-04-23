package com.catalis.core.kycb.web.controllers.identity.v1;

import com.catalis.core.kycb.interfaces.dtos.sanctions.v1.SanctionsQuestionnaireDTO;
import com.catalis.core.kycb.core.services.sanctions.v1.SanctionsQuestionnaireService;
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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/identity/parties/{partyId}/sanctions-questionnaire")
@Tag(name = "Sanctions Questionnaire", description = "API for managing sanctions and embargo questionnaires")
public class SanctionsQuestionnaireController {

    @Autowired
    private SanctionsQuestionnaireService sanctionsQuestionnaireService;

    @GetMapping("/{sanctionsQuestionnaireId}")
    @Operation(
            summary = "Get sanctions questionnaire",
            description = "Retrieves a sanctions questionnaire by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved sanctions questionnaire",
                            content = @Content(schema = @Schema(implementation = SanctionsQuestionnaireDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Sanctions questionnaire not found"
                    )
            }
    )
    public Mono<ResponseEntity<SanctionsQuestionnaireDTO>> getSanctionsQuestionnaire(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the sanctions questionnaire", required = true)
            @PathVariable Long sanctionsQuestionnaireId
    ) {
        return sanctionsQuestionnaireService.findById(sanctionsQuestionnaireId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/latest")
    @Operation(
            summary = "Get latest sanctions questionnaire",
            description = "Retrieves the latest sanctions questionnaire for a party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved latest sanctions questionnaire",
                            content = @Content(schema = @Schema(implementation = SanctionsQuestionnaireDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "No sanctions questionnaire found for the party"
                    )
            }
    )
    public Mono<ResponseEntity<SanctionsQuestionnaireDTO>> getLatestSanctionsQuestionnaire(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return sanctionsQuestionnaireService.findLatestByPartyId(partyId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(
            summary = "List sanctions questionnaires",
            description = "Retrieves all sanctions questionnaires for a party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved sanctions questionnaires",
                            content = @Content(schema = @Schema(implementation = SanctionsQuestionnaireDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<Flux<SanctionsQuestionnaireDTO>>> listSanctionsQuestionnaires(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return Mono.just(ResponseEntity.ok(sanctionsQuestionnaireService.findByPartyId(partyId)));
    }

    @PostMapping
    @Operation(
            summary = "Create sanctions questionnaire",
            description = "Creates a new sanctions questionnaire for a specific party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully created sanctions questionnaire",
                            content = @Content(schema = @Schema(implementation = SanctionsQuestionnaireDTO.class))
                    )
            }
    )
    public Mono<ResponseEntity<SanctionsQuestionnaireDTO>> createSanctionsQuestionnaire(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Sanctions questionnaire data", required = true)
            @RequestBody SanctionsQuestionnaireDTO sanctionsQuestionnaireDTO
    ) {
        sanctionsQuestionnaireDTO.setPartyId(partyId);
        return sanctionsQuestionnaireService.create(sanctionsQuestionnaireDTO)
                .map(dto -> ResponseEntity.status(HttpStatus.CREATED).body(dto));
    }

    @PatchMapping("/{sanctionsQuestionnaireId}")
    @Operation(
            summary = "Update sanctions questionnaire",
            description = "Updates a sanctions questionnaire by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated sanctions questionnaire",
                            content = @Content(schema = @Schema(implementation = SanctionsQuestionnaireDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Sanctions questionnaire not found"
                    )
            }
    )
    public Mono<ResponseEntity<SanctionsQuestionnaireDTO>> updateSanctionsQuestionnaire(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the sanctions questionnaire", required = true)
            @PathVariable Long sanctionsQuestionnaireId,
            @Parameter(description = "Updated sanctions questionnaire data", required = true)
            @RequestBody SanctionsQuestionnaireDTO sanctionsQuestionnaireDTO
    ) {
        sanctionsQuestionnaireDTO.setPartyId(partyId);
        return sanctionsQuestionnaireService.update(sanctionsQuestionnaireId, sanctionsQuestionnaireDTO)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{sanctionsQuestionnaireId}")
    @Operation(
            summary = "Delete sanctions questionnaire",
            description = "Deletes a sanctions questionnaire by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted sanctions questionnaire"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Sanctions questionnaire not found"
                    )
            }
    )
    public Mono<ResponseEntity<Void>> deleteSanctionsQuestionnaire(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the sanctions questionnaire", required = true)
            @PathVariable Long sanctionsQuestionnaireId
    ) {
        return sanctionsQuestionnaireService.delete(sanctionsQuestionnaireId)
                .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}