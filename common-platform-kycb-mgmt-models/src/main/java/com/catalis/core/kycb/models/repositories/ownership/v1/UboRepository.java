package com.catalis.core.kycb.models.repositories.ownership.v1;

import com.catalis.core.kycb.models.entities.ownership.v1.Ubo;
import com.catalis.core.kycb.interfaces.enums.ownership.v1.OwnershipTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Repository for Ultimate Beneficial Owner operations.
 */
@Repository
public interface UboRepository extends BaseRepository<Ubo, Long> {

    /**
     * Find UBOs by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of UBOs
     */
    Flux<Ubo> findByPartyId(Long partyId);

    /**
     * Find UBOs by natural person ID.
     *
     * @param naturalPersonId The ID of the natural person
     * @return A flux of UBOs
     */
    Flux<Ubo> findByNaturalPersonId(Long naturalPersonId);

    /**
     * Find UBOs by ownership type.
     *
     * @param ownershipTypeEnum The type of ownership
     * @return A flux of UBOs
     */
    Flux<Ubo> findByOwnershipType(OwnershipTypeEnum ownershipTypeEnum);

    /**
     * Find UBOs by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of UBOs
     */
    Flux<Ubo> findByIsVerified(Boolean isVerified);

    /**
     * Find UBOs with ownership percentage above a threshold.
     *
     * @param threshold The ownership percentage threshold
     * @return A flux of UBOs
     */
    Flux<Ubo> findByOwnershipPercentageGreaterThanEqual(BigDecimal threshold);

    /**
     * Find UBOs with ownership percentage below a threshold.
     *
     * @param threshold The ownership percentage threshold
     * @return A flux of UBOs
     */
    Flux<Ubo> findByOwnershipPercentageLessThan(BigDecimal threshold);

    /**
     * Find UBOs by verification date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of UBOs
     */
    Flux<Ubo> findByVerificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find active UBOs (not ended).
     *
     * @param currentDate The current date
     * @return A flux of UBOs
     */
    Flux<Ubo> findByEndDateAfterOrEndDateIsNull(LocalDateTime currentDate);

    /**
     * Find UBOs by party ID and ownership percentage above a threshold.
     *
     * @param partyId The ID of the party
     * @param threshold The ownership percentage threshold
     * @return A flux of UBOs
     */
    Flux<Ubo> findByPartyIdAndOwnershipPercentageGreaterThanEqual(Long partyId, BigDecimal threshold);
}
