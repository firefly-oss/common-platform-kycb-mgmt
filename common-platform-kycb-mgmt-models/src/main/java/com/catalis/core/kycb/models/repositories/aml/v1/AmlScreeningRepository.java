package com.catalis.core.kycb.models.repositories.aml.v1;

import com.catalis.core.kycb.models.entities.aml.v1.AmlScreening;
import com.catalis.core.kycb.interfaces.enums.screening.v1.ScreeningResultEnum;
import com.catalis.core.kycb.interfaces.enums.screening.v1.ScreeningTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for AML screening operations.
 */
@Repository
public interface AmlScreeningRepository extends BaseRepository<AmlScreening, Long> {

    /**
     * Find AML screenings by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of AML screenings
     */
    Flux<AmlScreening> findByPartyId(Long partyId);

    /**
     * Find AML screenings by screening type.
     *
     * @param screeningTypeEnum The type of screening
     * @return A flux of AML screenings
     */
    Flux<AmlScreening> findByScreeningType(ScreeningTypeEnum screeningTypeEnum);

    /**
     * Find AML screenings by screening result.
     *
     * @param screeningResultEnum The result of screening
     * @return A flux of AML screenings
     */
    Flux<AmlScreening> findByScreeningResult(ScreeningResultEnum screeningResultEnum);

    /**
     * Find AML screenings by matches found flag.
     *
     * @param matchesFound The matches found flag
     * @return A flux of AML screenings
     */
    Flux<AmlScreening> findByMatchesFound(Boolean matchesFound);

    /**
     * Find AML screenings by screening date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of AML screenings
     */
    Flux<AmlScreening> findByScreeningDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find AML screenings that need to be rescreened.
     *
     * @param currentDate The current date
     * @return A flux of AML screenings
     */
    Flux<AmlScreening> findByNextScreeningDateBefore(LocalDateTime currentDate);

    /**
     * Find the latest AML screening for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest AML screening
     */
    Mono<AmlScreening> findFirstByPartyIdOrderByScreeningDateDesc(Long partyId);
}