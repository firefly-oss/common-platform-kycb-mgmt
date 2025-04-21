package com.catalis.core.kycb.web.controllers.corporate.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.services.corporate.v1.CorporateStructureService;
import com.catalis.core.kycb.interfaces.dtos.corporate.v1.CorporateStructureDTO;
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
@RequestMapping("/api/v1/corporate/parties/{partyId}/structure")
@Tag(name = "Corporate Structure", description = "API for managing corporate structure relationships")
public class CorporateStructureController {

    @Autowired
    private CorporateStructureService corporateStructureService;

    @GetMapping
    @Operation(
            summary = "Get corporate structure",
            description = "Retrieves all corporate structure relationships for the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved corporate structure relationships",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    )
            }
    )
    public Flux<CorporateStructureDTO> getCorporateStructure(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId
    ) {
        return corporateStructureService.findByPartyId(partyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(
            summary = "Add structure relationship",
            description = "Adds a new structure relationship to the specified party",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Successfully added structure relationship",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    )
            }
    )
    public Mono<CorporateStructureDTO> addStructureRelationship(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Structure relationship data", required = true)
            @RequestBody CorporateStructureDTO corporateStructureDTO
    ) {
        corporateStructureDTO.setPartyId(partyId);
        return corporateStructureService.create(corporateStructureDTO);
    }

    @GetMapping("/{structureId}")
    @Operation(
            summary = "Get specific structure relationship",
            description = "Retrieves a specific structure relationship by its ID",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved structure relationship",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Structure relationship not found"
                    )
            }
    )
    public Mono<CorporateStructureDTO> getStructureRelationship(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the structure relationship", required = true)
            @PathVariable Long structureId
    ) {
        return corporateStructureService.getById(structureId);
    }

    @PatchMapping("/{structureId}")
    @Operation(
            summary = "Update structure relationship",
            description = "Updates an existing structure relationship",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated structure relationship",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Structure relationship not found"
                    )
            }
    )
    public Mono<CorporateStructureDTO> updateStructureRelationship(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the structure relationship", required = true)
            @PathVariable Long structureId,
            @Parameter(description = "Updated structure relationship data", required = true)
            @RequestBody CorporateStructureDTO corporateStructureDTO
    ) {
        corporateStructureDTO.setPartyId(partyId);
        return corporateStructureService.update(structureId, corporateStructureDTO);
    }

    @DeleteMapping("/{structureId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Operation(
            summary = "Delete structure relationship",
            description = "Deletes a structure relationship",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Successfully deleted structure relationship"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Structure relationship not found"
                    )
            }
    )
    public Mono<Void> deleteStructureRelationship(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the structure relationship", required = true)
            @PathVariable Long structureId
    ) {
        return corporateStructureService.delete(structureId);
    }

    @PostMapping("/{structureId}/verify")
    @Operation(
            summary = "Verify structure relationship",
            description = "Marks a structure relationship as verified",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully verified structure relationship",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Structure relationship not found"
                    )
            }
    )
    public Mono<CorporateStructureDTO> verifyStructureRelationship(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the structure relationship", required = true)
            @PathVariable Long structureId,
            @Parameter(description = "Verification notes")
            @RequestParam(required = false) String verificationNotes
    ) {
        return corporateStructureService.verifyStructure(structureId, verificationNotes);
    }

    @GetMapping("/parent/{parentId}")
    @Operation(
            summary = "Get structure by parent",
            description = "Retrieves all structure relationships where the specified entity is the parent",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved structure relationships",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    )
            }
    )
    public Flux<CorporateStructureDTO> getStructureByParent(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "ID of the parent entity", required = true)
            @PathVariable Long parentId
    ) {
        return corporateStructureService.findByParentId(parentId)
                .filter(structure -> structure.getPartyId().equals(partyId));
    }

    @GetMapping("/type/{relationshipType}")
    @Operation(
            summary = "Get structure by type",
            description = "Retrieves all structure relationships of a specific type",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully retrieved structure relationships",
                            content = @Content(schema = @Schema(implementation = CorporateStructureDTO.class))
                    )
            }
    )
    public Flux<CorporateStructureDTO> getStructureByType(
            @Parameter(description = "ID of the party", required = true)
            @PathVariable Long partyId,
            @Parameter(description = "Type of relationship", required = true)
            @PathVariable String relationshipType
    ) {
        return corporateStructureService.findByRelationshipType(relationshipType)
                .filter(structure -> structure.getPartyId().equals(partyId));
    }
}
