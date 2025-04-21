package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for compliance case operations.
 */
public interface ComplianceCaseService {
    /**
     * Retrieves all compliance cases based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving compliance cases.
     * @return A {@link Mono} containing a paginated response of compliance case DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<ComplianceCaseDTO>> findAll(FilterRequest<ComplianceCaseDTO> filterRequest);
    
    /**
     * Retrieves all compliance cases for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of compliance case DTOs.
     */
    Flux<ComplianceCaseDTO> findByPartyId(Long partyId);
    
    /**
     * Creates a new compliance case entry based on the provided data transfer object.
     *
     * @param dto The ComplianceCaseDTO containing data to create a new compliance case record
     * @return A Mono containing the created ComplianceCaseDTO
     */
    Mono<ComplianceCaseDTO> create(ComplianceCaseDTO dto);
    
    /**
     * Retrieves a ComplianceCaseDTO by its unique identifier.
     *
     * @param caseId The ID of the ComplianceCase to retrieve.
     * @return A Mono containing the ComplianceCaseDTO if found, otherwise an empty mono.
     */
    Mono<ComplianceCaseDTO> getById(Long caseId);
    
    /**
     * Updates an existing ComplianceCase entry with new data provided in the DTO.
     *
     * @param caseId The ID of the ComplianceCase to be updated.
     * @param dto A DTO containing the fields to update for the ComplianceCase.
     * @return A Mono containing the updated ComplianceCaseDTO if successful.
     */
    Mono<ComplianceCaseDTO> update(Long caseId, ComplianceCaseDTO dto);
    
    /**
     * Retrieves all compliance cases with a specific status.
     *
     * @param status The status of the compliance cases to retrieve.
     * @return A {@link Flux} of compliance case DTOs.
     */
    Flux<ComplianceCaseDTO> findByStatus(String status);
    
    /**
     * Retrieves all compliance cases with a specific priority.
     *
     * @param priority The priority of the compliance cases to retrieve.
     * @return A {@link Flux} of compliance case DTOs.
     */
    Flux<ComplianceCaseDTO> findByPriority(String priority);
    
    /**
     * Retrieves all compliance cases of a specific type.
     *
     * @param type The type of the compliance cases to retrieve.
     * @return A {@link Flux} of compliance case DTOs.
     */
    Flux<ComplianceCaseDTO> findByType(String type);
    
    /**
     * Assigns a compliance case to a specific agent.
     *
     * @param caseId The ID of the compliance case to assign.
     * @param assignedTo The ID or name of the agent to assign the case to.
     * @return A Mono containing the updated ComplianceCaseDTO.
     */
    Mono<ComplianceCaseDTO> assignCase(Long caseId, String assignedTo);
    
    /**
     * Updates the status of a compliance case.
     *
     * @param caseId The ID of the compliance case to update.
     * @param status The new status of the case.
     * @param statusNotes Notes explaining the status change.
     * @return A Mono containing the updated ComplianceCaseDTO.
     */
    Mono<ComplianceCaseDTO> updateStatus(Long caseId, String status, String statusNotes);
}