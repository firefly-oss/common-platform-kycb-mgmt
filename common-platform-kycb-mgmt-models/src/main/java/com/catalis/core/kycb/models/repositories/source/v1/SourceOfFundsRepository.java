package com.catalis.core.kycb.models.repositories.source.v1;

import com.catalis.core.kycb.models.entities.source.v1.SourceOfFunds;
import com.catalis.core.kycb.interfaces.enums.source.v1.SourceTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Repository for source of funds operations.
 */
@Repository
public interface SourceOfFundsRepository extends BaseRepository<SourceOfFunds, Long> {

    /**
     * Find sources of funds by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findByPartyId(Long partyId);

    /**
     * Find sources of funds by source type.
     *
     * @param sourceTypeEnum The type of source
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findBySourceType(SourceTypeEnum sourceTypeEnum);

    /**
     * Find sources of funds by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findByIsVerified(Boolean isVerified);

    /**
     * Find sources of funds with estimated annual amount above a threshold.
     *
     * @param threshold The amount threshold
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findByEstimatedAnnualAmountGreaterThanEqual(BigDecimal threshold);

    /**
     * Find sources of funds by verification date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findByVerificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find sources of funds by currency.
     *
     * @param currency The currency code
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findByCurrency(String currency);

    /**
     * Find sources of funds by party ID and source type.
     *
     * @param partyId The ID of the party
     * @param sourceTypeEnum The type of source
     * @return A flux of sources of funds
     */
    Flux<SourceOfFunds> findByPartyIdAndSourceType(Long partyId, SourceTypeEnum sourceTypeEnum);

    /**
     * Find the latest source of funds for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest source of funds
     */
    Mono<SourceOfFunds> findFirstByPartyIdOrderByDateCreatedDesc(Long partyId);
}