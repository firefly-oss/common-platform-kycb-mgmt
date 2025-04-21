package com.catalis.core.kycb.models.repositories.power.v1;

import com.catalis.core.kycb.models.entities.power.v1.PowerOfAttorney;
import com.catalis.core.kycb.interfaces.enums.power.v1.PowerTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * Repository for power of attorney operations.
 */
@Repository
public interface PowerOfAttorneyRepository extends BaseRepository<PowerOfAttorney, Long> {

    /**
     * Find powers of attorney by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByPartyId(Long partyId);

    /**
     * Find powers of attorney by attorney ID.
     *
     * @param attorneyId The ID of the attorney
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByAttorneyId(Long attorneyId);

    /**
     * Find powers of attorney by corporate document ID.
     *
     * @param corporateDocumentId The ID of the corporate document
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByCorporateDocumentId(Long corporateDocumentId);

    /**
     * Find powers of attorney by power type.
     *
     * @param powerTypeEnum The type of power
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByPowerType(PowerTypeEnum powerTypeEnum);

    /**
     * Find powers of attorney by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByIsVerified(Boolean isVerified);

    /**
     * Find powers of attorney by bastanteo status.
     *
     * @param isBastanteoCompleted The bastanteo status
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByIsBastanteoCompleted(Boolean isBastanteoCompleted);

    /**
     * Find powers of attorney that are about to expire.
     *
     * @param expiryDate The expiry date threshold
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByExpiryDateBefore(LocalDateTime expiryDate);

    /**
     * Find active powers of attorney (not expired).
     *
     * @param currentDate The current date
     * @return A flux of powers of attorney
     */
    Flux<PowerOfAttorney> findByExpiryDateAfterOrExpiryDateIsNull(LocalDateTime currentDate);
}
