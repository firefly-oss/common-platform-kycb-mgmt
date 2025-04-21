package com.catalis.core.kycb.models.repositories.edd.v1;

import com.catalis.core.kycb.models.entities.edd.v1.EnhancedDueDiligence;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddReasonEnum;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddStatusEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for enhanced due diligence operations.
 */
@Repository
public interface EnhancedDueDiligenceRepository extends BaseRepository<EnhancedDueDiligence, Long> {

    /**
     * Find enhanced due diligence records by KYC verification ID.
     *
     * @param kycVerificationId The ID of the KYC verification
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByKycVerificationId(Long kycVerificationId);

    /**
     * Find enhanced due diligence records by EDD reason.
     *
     * @param eddReasonEnum The reason for EDD
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByEddReason(EddReasonEnum eddReasonEnum);

    /**
     * Find enhanced due diligence records by EDD status.
     *
     * @param eddStatusEnum The status of EDD
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByEddStatus(EddStatusEnum eddStatusEnum);

    /**
     * Find enhanced due diligence records by approving authority.
     *
     * @param approvingAuthority The approving authority
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByApprovingAuthority(String approvingAuthority);

    /**
     * Find enhanced due diligence records by internal committee approval status.
     *
     * @param internalCommitteeApproval The internal committee approval status
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByInternalCommitteeApproval(Boolean internalCommitteeApproval);

    /**
     * Find enhanced due diligence records by approval date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByApprovalDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find enhanced due diligence records by completion date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByCompletionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find enhanced due diligence records by completed by.
     *
     * @param completedBy The user who completed the EDD
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByCompletedBy(String completedBy);

    /**
     * Find enhanced due diligence records by KYC verification ID and EDD status.
     *
     * @param kycVerificationId The ID of the KYC verification
     * @param eddStatusEnum The status of EDD
     * @return A flux of enhanced due diligence records
     */
    Flux<EnhancedDueDiligence> findByKycVerificationIdAndEddStatus(Long kycVerificationId, EddStatusEnum eddStatusEnum);

    /**
     * Find the latest enhanced due diligence record for a KYC verification.
     *
     * @param kycVerificationId The ID of the KYC verification
     * @return A mono with the latest enhanced due diligence record
     */
    Mono<EnhancedDueDiligence> findFirstByKycVerificationIdOrderByDateCreatedDesc(Long kycVerificationId);
}