package com.catalis.core.kycb.models.repositories.aml.v1;

import com.catalis.core.kycb.models.entities.aml.v1.AmlMatch;
import com.catalis.core.kycb.interfaces.enums.aml.v1.ListTypeEnum;
import com.catalis.core.kycb.interfaces.enums.resolution.v1.ResolutionStatusEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Repository for AML match operations.
 */
@Repository
public interface AmlMatchRepository extends BaseRepository<AmlMatch, Long> {

    /**
     * Find AML matches by AML screening ID.
     *
     * @param amlScreeningId The ID of the AML screening
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByAmlScreeningId(Long amlScreeningId);

    /**
     * Find AML matches by list type.
     *
     * @param listTypeEnum The type of list
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByListType(ListTypeEnum listTypeEnum);

    /**
     * Find AML matches by resolution status.
     *
     * @param resolutionStatusEnum The status of resolution
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByResolutionStatus(ResolutionStatusEnum resolutionStatusEnum);

    /**
     * Find AML matches by resolution agent.
     *
     * @param resolutionAgent The agent who resolved the match
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByResolutionAgent(String resolutionAgent);

    /**
     * Find AML matches by resolution date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByResolutionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find AML matches with a score above a threshold.
     *
     * @param threshold The score threshold
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByMatchScoreGreaterThanEqual(BigDecimal threshold);

    /**
     * Find unresolved AML matches.
     *
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByResolutionStatusIsNull();

    /**
     * Find AML matches by AML screening ID and list type.
     *
     * @param amlScreeningId The ID of the AML screening
     * @param listTypeEnum The type of list
     * @return A flux of AML matches
     */
    Flux<AmlMatch> findByAmlScreeningIdAndListType(Long amlScreeningId, ListTypeEnum listTypeEnum);
}