package com.catalis.core.kycb.models.repositories.economic.v1;

import com.catalis.core.kycb.models.entities.economic.v1.EconomicActivity;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for economic activity operations.
 */
@Repository
public interface EconomicActivityRepository extends BaseRepository<EconomicActivity, Long> {

    /**
     * Find economic activities by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByPartyId(Long partyId);

    /**
     * Find economic activities by activity code.
     *
     * @param activityCode The activity code
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByActivityCode(String activityCode);

    /**
     * Find primary economic activities.
     *
     * @param isPrimary The primary flag
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByIsPrimary(Boolean isPrimary);

    /**
     * Find economic activities by sector code.
     *
     * @param sectorCode The sector code
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findBySectorCode(String sectorCode);

    /**
     * Find economic activities by subsector.
     *
     * @param subsector The subsector
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findBySubsector(String subsector);

    /**
     * Find high-risk economic activities.
     *
     * @param highRiskActivity The high-risk flag
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByHighRiskActivity(Boolean highRiskActivity);

    /**
     * Find economic activities by geographic scope code.
     *
     * @param geographicScopeCode The geographic scope code
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByGeographicScopeCode(String geographicScopeCode);

    /**
     * Find economic activities by export markets containing a specific country.
     *
     * @param countryCode The country code to search for
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByExportMarketsContaining(String countryCode);

    /**
     * Find economic activities by import markets containing a specific country.
     *
     * @param countryCode The country code to search for
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByImportMarketsContaining(String countryCode);

    /**
     * Find regulated economic activities.
     *
     * @param regulatedActivity The regulated activity flag
     * @return A flux of economic activities
     */
    Flux<EconomicActivity> findByRegulatedActivity(Boolean regulatedActivity);

    /**
     * Find the primary economic activity for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the primary economic activity
     */
    Mono<EconomicActivity> findByPartyIdAndIsPrimaryTrue(Long partyId);
}
