package com.catalis.core.kycb.models.repositories.expected.v1;

import com.catalis.core.kycb.models.entities.expected.v1.ExpectedActivity;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

/**
 * Repository for expected activity operations.
 */
@Repository
public interface ExpectedActivityRepository extends BaseRepository<ExpectedActivity, Long> {

    /**
     * Find expected activities by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByPartyId(Long partyId);

    /**
     * Find expected activities by activity type code.
     *
     * @param activityTypeCode The activity type code
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByActivityTypeCode(String activityTypeCode);

    /**
     * Find expected activities with monthly volume above a threshold.
     *
     * @param threshold The monthly volume threshold
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByExpectedMonthlyVolumeGreaterThanEqual(BigDecimal threshold);

    /**
     * Find expected activities with annual volume above a threshold.
     *
     * @param threshold The annual volume threshold
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByExpectedAnnualVolumeGreaterThanEqual(BigDecimal threshold);

    /**
     * Find expected activities with transaction count above a threshold.
     *
     * @param threshold The transaction count threshold
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByExpectedTransactionCountGreaterThanEqual(Integer threshold);

    /**
     * Find expected activities by currency.
     *
     * @param currencyIsoCode The currency ISO code
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByCurrencyIsoCode(String currencyIsoCode);

    /**
     * Find expected activities by transaction frequency code.
     *
     * @param transactionFreqCode The transaction frequency code
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByTransactionFreqCode(String transactionFreqCode);

    /**
     * Find expected activities by anticipated countries containing a specific country.
     *
     * @param countryCode The country code to search for
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByAnticipatedCountriesContaining(String countryCode);

    /**
     * Find high-value expected activities.
     *
     * @param isHighValue The high-value flag
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByIsHighValue(Boolean isHighValue);

    /**
     * Find cash-intensive expected activities.
     *
     * @param cashIntensive The cash-intensive flag
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByCashIntensive(Boolean cashIntensive);

    /**
     * Find expected activities with tax haven transactions.
     *
     * @param taxHavenTransactions The tax haven transactions flag
     * @return A flux of expected activities
     */
    Flux<ExpectedActivity> findByTaxHavenTransactions(Boolean taxHavenTransactions);

    /**
     * Find the latest expected activity for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest expected activity
     */
    Mono<ExpectedActivity> findFirstByPartyIdOrderByDateCreatedDesc(Long partyId);
}