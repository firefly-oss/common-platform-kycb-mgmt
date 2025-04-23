package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
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
     * Deletes a Compliance Case by its ID.
     *
     * @param caseId The ID of the Compliance Case to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long caseId);
}
