package com.catalis.core.kycb.models.repositories.kyc.v1;

import com.catalis.core.kycb.models.entities.kyc.v1.KycVerification;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for KYC verification operations.
 */
@Repository
public interface KycVerificationRepository extends BaseRepository<KycVerification, Long> {

    /**
     * Find KYC verifications by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of KYC verifications
     */
    Flux<KycVerification> findByPartyId(Long partyId);

    /**
     * Find the latest KYC verification for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest KYC verification
     */
    Mono<KycVerification> findFirstByPartyIdOrderByVerificationDateDesc(Long partyId);
}