package com.catalis.core.kycb.core.services.industry.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import reactor.core.publisher.Mono;

/**
 * Service interface for industry risk operations.
 */
public interface IndustryRiskService {
    /**
     * Retrieves all industry risks based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving industry risks.
     * @return A {@link Mono} containing a paginated response of industry risk DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<IndustryRiskDTO>> findAll(FilterRequest<IndustryRiskDTO> filterRequest);

    /**
     * Creates a new industry risk entry based on the provided data transfer object.
     *
     * @param dto The IndustryRiskDTO containing data to create a new industry risk record
     * @return A Mono containing the created IndustryRiskDTO
     */
    Mono<IndustryRiskDTO> create(IndustryRiskDTO dto);

    /**
     * Retrieves an IndustryRiskDTO by its unique identifier.
     *
     * @param industryRiskId The ID of the IndustryRisk to retrieve.
     * @return A Mono containing the IndustryRiskDTO if found, otherwise an empty mono.
     */
    Mono<IndustryRiskDTO> getById(Long industryRiskId);

    /**
     * Updates an existing IndustryRisk entry with new data provided in the DTO.
     *
     * @param industryRiskId The ID of the IndustryRisk to be updated.
     * @param dto A DTO containing the fields to update for the IndustryRisk.
     * @return A Mono containing the updated IndustryRiskDTO if successful.
     */
    Mono<IndustryRiskDTO> update(Long industryRiskId, IndustryRiskDTO dto);

    /**
     * Deletes an Industry Risk by its ID.
     *
     * @param industryRiskId The ID of the Industry Risk to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long industryRiskId);
}
