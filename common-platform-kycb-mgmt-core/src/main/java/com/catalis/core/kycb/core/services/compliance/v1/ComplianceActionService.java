package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceActionDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for compliance action operations.
 */
public interface ComplianceActionService {
    /**
     * Retrieves all compliance actions based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving compliance actions.
     * @return A {@link Mono} containing a paginated response of compliance action DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<ComplianceActionDTO>> findAll(FilterRequest<ComplianceActionDTO> filterRequest);

    /**
     * Creates a new compliance action entry based on the provided data transfer object.
     *
     * @param dto The ComplianceActionDTO containing data to create a new compliance action record
     * @return A Mono containing the created ComplianceActionDTO
     */
    Mono<ComplianceActionDTO> create(ComplianceActionDTO dto);

    /**
     * Retrieves a ComplianceActionDTO by its unique identifier.
     *
     * @param actionId The ID of the ComplianceAction to retrieve.
     * @return A Mono containing the ComplianceActionDTO if found, otherwise an empty mono.
     */
    Mono<ComplianceActionDTO> getById(Long actionId);

    /**
     * Updates an existing ComplianceAction entry with new data provided in the DTO.
     *
     * @param actionId The ID of the ComplianceAction to be updated.
     * @param dto A DTO containing the fields to update for the ComplianceAction.
     * @return A Mono containing the updated ComplianceActionDTO if successful.
     */
    Mono<ComplianceActionDTO> update(Long actionId, ComplianceActionDTO dto);

    /**
     * Deletes a Compliance Action by its ID.
     *
     * @param actionId The ID of the Compliance Action to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long actionId);
}
