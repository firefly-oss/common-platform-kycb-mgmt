package com.catalis.core.kycb.core.services.business.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.business.v1.BusinessProfileDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service interface for business profile operations.
 */
public interface BusinessProfileService {
    /**
     * Retrieves all business profiles based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving business profiles.
     * @return A {@link Mono} containing a paginated response of business profile DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<BusinessProfileDTO>> findAll(FilterRequest<BusinessProfileDTO> filterRequest);

    /**
     * Retrieves all business profiles for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByPartyId(Long partyId);

    /**
     * Creates a new business profile entry based on the provided data transfer object.
     *
     * @param dto The BusinessProfileDTO containing data to create a new business profile record
     * @return A Mono containing the created BusinessProfileDTO
     */
    Mono<BusinessProfileDTO> create(BusinessProfileDTO dto);

    /**
     * Retrieves a BusinessProfileDTO by its unique identifier.
     *
     * @param businessProfileId The ID of the BusinessProfile to retrieve.
     * @return A Mono containing the BusinessProfileDTO if found, otherwise an empty mono.
     */
    Mono<BusinessProfileDTO> getById(Long businessProfileId);

    /**
     * Updates an existing BusinessProfile entry with new data provided in the DTO.
     *
     * @param businessProfileId The ID of the BusinessProfile to be updated.
     * @param dto A DTO containing the fields to update for the BusinessProfile.
     * @return A Mono containing the updated BusinessProfileDTO if successful.
     */
    Mono<BusinessProfileDTO> update(Long businessProfileId, BusinessProfileDTO dto);

    /**
     * Deletes a Business Profile by its ID.
     *
     * @param businessProfileId The ID of the Business Profile to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long businessProfileId);

    /**
     * Retrieves the latest business profile for a party.
     *
     * @param partyId The ID of the party.
     * @return A Mono containing the latest BusinessProfileDTO.
     */
    Mono<BusinessProfileDTO> getLatestByPartyId(Long partyId);

    /**
     * Retrieves all business profiles with a specific legal form.
     *
     * @param legalFormCode The legal form code.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByLegalFormCode(String legalFormCode);

    /**
     * Retrieves all business profiles with a specific incorporation year.
     *
     * @param incorporationYear The incorporation year.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByIncorporationYear(Integer incorporationYear);

    /**
     * Retrieves all business profiles with an employee count in the specified range.
     *
     * @param minEmployees The minimum number of employees.
     * @param maxEmployees The maximum number of employees.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByEmployeeCountBetween(Integer minEmployees, Integer maxEmployees);

    /**
     * Retrieves all business profiles with a specific employee range code.
     *
     * @param employeeRangeCode The employee range code.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByEmployeeRangeCode(String employeeRangeCode);

    /**
     * Retrieves all business profiles with an annual revenue in the specified range.
     *
     * @param minRevenue The minimum annual revenue.
     * @param maxRevenue The maximum annual revenue.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByAnnualRevenueBetween(java.math.BigDecimal minRevenue, java.math.BigDecimal maxRevenue);

    /**
     * Retrieves all business profiles with a specific revenue range code.
     *
     * @param revenueRangeCode The revenue range code.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByRevenueRangeCode(String revenueRangeCode);

    /**
     * Retrieves all business profiles with a specific regulated status.
     *
     * @param isRegulated The regulated status.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByIsRegulated(Boolean isRegulated);

    /**
     * Retrieves all business profiles with a specific regulatory authority.
     *
     * @param regulatoryAuthority The regulatory authority.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByRegulatoryAuthority(String regulatoryAuthority);

    /**
     * Retrieves all business profiles with a specific company status code.
     *
     * @param companyStatusCode The company status code.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByCompanyStatusCode(String companyStatusCode);

    /**
     * Retrieves all business profiles with a specific public entity status.
     *
     * @param isPublicEntity The public entity status.
     * @return A {@link Flux} of business profile DTOs.
     */
    Flux<BusinessProfileDTO> findByIsPublicEntity(Boolean isPublicEntity);
}
