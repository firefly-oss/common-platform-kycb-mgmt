package com.catalis.core.kycb.core.services.location.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.interfaces.dtos.location.v1.BusinessLocationDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Service interface for business location operations.
 */
public interface BusinessLocationService {
    /**
     * Retrieves all business locations based on specified filter criteria.
     *
     * @param filterRequest The object containing filter criteria to apply for retrieving business locations.
     * @return A {@link Mono} containing a paginated response of business location DTOs that match the filter criteria.
     */
    Mono<PaginationResponse<BusinessLocationDTO>> findAll(FilterRequest<BusinessLocationDTO> filterRequest);

    /**
     * Retrieves all business locations for a specific party.
     *
     * @param partyId The ID of the party.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByPartyId(Long partyId);

    /**
     * Creates a new business location entry based on the provided data transfer object.
     *
     * @param dto The BusinessLocationDTO containing data to create a new business location record
     * @return A Mono containing the created BusinessLocationDTO
     */
    Mono<BusinessLocationDTO> create(BusinessLocationDTO dto);

    /**
     * Retrieves a BusinessLocationDTO by its unique identifier.
     *
     * @param locationId The ID of the BusinessLocation to retrieve.
     * @return A Mono containing the BusinessLocationDTO if found, otherwise an empty mono.
     */
    Mono<BusinessLocationDTO> getById(Long locationId);

    /**
     * Updates an existing BusinessLocation entry with new data provided in the DTO.
     *
     * @param locationId The ID of the BusinessLocation to be updated.
     * @param dto A DTO containing the fields to update for the BusinessLocation.
     * @return A Mono containing the updated BusinessLocationDTO if successful.
     */
    Mono<BusinessLocationDTO> update(Long locationId, BusinessLocationDTO dto);

    /**
     * Deletes a Business Location by its ID.
     *
     * @param locationId The ID of the Business Location to delete.
     * @return A {@link Mono<Void>} indicating completion of the deletion operation.
     */
    Mono<Void> delete(Long locationId);

    /**
     * Retrieves all business locations of a specific type.
     *
     * @param locationTypeCode The type code of the business locations to retrieve.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByLocationTypeCode(String locationTypeCode);

    /**
     * Retrieves all primary business locations.
     *
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findPrimaryLocations();

    /**
     * Retrieves all business locations in a specific country.
     *
     * @param countryIsoCode The country ISO code.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByCountry(String countryIsoCode);

    /**
     * Retrieves all business locations in a specific city.
     *
     * @param city The city.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByCity(String city);

    /**
     * Retrieves all business locations with employee count between the specified range.
     *
     * @param minEmployees The minimum number of employees.
     * @param maxEmployees The maximum number of employees.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByEmployeeCountBetween(Integer minEmployees, Integer maxEmployees);

    /**
     * Retrieves all verified business locations.
     *
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findVerifiedLocations();

    /**
     * Retrieves all business locations verified using a specific method.
     *
     * @param verificationMethod The verification method.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByVerificationMethod(String verificationMethod);

    /**
     * Retrieves all business locations verified between the specified date range.
     *
     * @param startDate The start date.
     * @param endDate The end date.
     * @return A {@link Flux} of business location DTOs.
     */
    Flux<BusinessLocationDTO> findByVerificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Retrieves the primary business location for a party.
     *
     * @param partyId The ID of the party.
     * @return A Mono containing the primary BusinessLocationDTO.
     */
    Mono<BusinessLocationDTO> getPrimaryLocationByPartyId(Long partyId);

    /**
     * Sets a business location as the primary location for a party.
     *
     * @param locationId The ID of the business location to set as primary.
     * @return A Mono containing the updated BusinessLocationDTO.
     */
    Mono<BusinessLocationDTO> setPrimaryLocation(Long locationId);

    /**
     * Verifies a business location.
     *
     * @param locationId The ID of the business location to verify.
     * @param verificationMethod The method used for verification.
     * @return A Mono containing the updated BusinessLocationDTO.
     */
    Mono<BusinessLocationDTO> verifyLocation(Long locationId, String verificationMethod);
}
