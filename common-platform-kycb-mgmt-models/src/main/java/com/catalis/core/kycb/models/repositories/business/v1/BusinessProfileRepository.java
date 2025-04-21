package com.catalis.core.kycb.models.repositories.business.v1;

import com.catalis.core.kycb.models.entities.business.v1.BusinessProfile;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * Repository for business profile operations.
 */
@Repository
public interface BusinessProfileRepository extends BaseRepository<BusinessProfile, Long> {

    /**
     * Find business profiles by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByPartyId(Long partyId);

    /**
     * Find business profiles by legal form code.
     *
     * @param legalFormCode The legal form code
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByLegalFormCode(String legalFormCode);

    /**
     * Find business profiles by incorporation year.
     *
     * @param incorporationYear The incorporation year
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByIncorporationYear(Integer incorporationYear);

    /**
     * Find business profiles by employee count range.
     *
     * @param minEmployees The minimum number of employees
     * @param maxEmployees The maximum number of employees
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByEmployeeCountBetween(Integer minEmployees, Integer maxEmployees);

    /**
     * Find business profiles by employee range code.
     *
     * @param employeeRangeCode The employee range code
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByEmployeeRangeCode(String employeeRangeCode);

    /**
     * Find business profiles by annual revenue range.
     *
     * @param minRevenue The minimum annual revenue
     * @param maxRevenue The maximum annual revenue
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByAnnualRevenueBetween(BigDecimal minRevenue, BigDecimal maxRevenue);

    /**
     * Find business profiles by revenue range code.
     *
     * @param revenueRangeCode The revenue range code
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByRevenueRangeCode(String revenueRangeCode);

    /**
     * Find business profiles by regulated status.
     *
     * @param isRegulated The regulated status
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByIsRegulated(Boolean isRegulated);

    /**
     * Find business profiles by regulatory authority.
     *
     * @param regulatoryAuthority The regulatory authority
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByRegulatoryAuthority(String regulatoryAuthority);

    /**
     * Find business profiles by company status code.
     *
     * @param companyStatusCode The company status code
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByCompanyStatusCode(String companyStatusCode);

    /**
     * Find business profiles by public entity status.
     *
     * @param isPublicEntity The public entity status
     * @return A flux of business profiles
     */
    Flux<BusinessProfile> findByIsPublicEntity(Boolean isPublicEntity);

    /**
     * Find the latest business profile for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest business profile
     */
    Mono<BusinessProfile> findFirstByPartyIdOrderByDateCreatedDesc(Long partyId);
}
