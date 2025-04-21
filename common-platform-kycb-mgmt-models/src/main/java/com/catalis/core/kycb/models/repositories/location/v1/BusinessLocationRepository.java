package com.catalis.core.kycb.models.repositories.location.v1;

import com.catalis.core.kycb.models.entities.location.v1.BusinessLocation;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for business location operations.
 */
@Repository
public interface BusinessLocationRepository extends BaseRepository<BusinessLocation, Long> {

    /**
     * Find business locations by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByPartyId(Long partyId);

    /**
     * Find business locations by location type code.
     *
     * @param locationTypeCode The location type code
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByLocationTypeCode(String locationTypeCode);

    /**
     * Find primary business locations.
     *
     * @param isPrimary The primary flag
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByIsPrimary(Boolean isPrimary);

    /**
     * Find business locations by postal code.
     *
     * @param postalCode The postal code
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByPostalCode(String postalCode);

    /**
     * Find business locations by city.
     *
     * @param city The city
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByCity(String city);

    /**
     * Find business locations by division code.
     *
     * @param divisionCode The division code
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByDivisionCode(String divisionCode);

    /**
     * Find business locations by country ISO code.
     *
     * @param countryIsoCode The country ISO code
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByCountryIsoCode(String countryIsoCode);

    /**
     * Find business locations by employee count range.
     *
     * @param minEmployees The minimum number of employees
     * @param maxEmployees The maximum number of employees
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByEmployeeCountBetween(Integer minEmployees, Integer maxEmployees);

    /**
     * Find business locations by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByIsVerified(Boolean isVerified);

    /**
     * Find business locations by verification method.
     *
     * @param verificationMethod The verification method
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByVerificationMethod(String verificationMethod);

    /**
     * Find business locations by verification date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of business locations
     */
    Flux<BusinessLocation> findByVerificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find the primary business location for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the primary business location
     */
    Mono<BusinessLocation> findByPartyIdAndIsPrimaryTrue(Long partyId);
}
