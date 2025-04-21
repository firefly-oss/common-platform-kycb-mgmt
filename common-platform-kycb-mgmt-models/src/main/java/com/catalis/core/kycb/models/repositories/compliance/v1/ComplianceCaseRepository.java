package com.catalis.core.kycb.models.repositories.compliance.v1;

import com.catalis.core.kycb.models.entities.compliance.v1.ComplianceCase;
import com.catalis.core.kycb.interfaces.enums.compliance.v1.CasePriorityEnum;
import com.catalis.core.kycb.interfaces.enums.compliance.v1.CaseStatusEnum;
import com.catalis.core.kycb.interfaces.enums.compliance.v1.CaseTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for compliance case operations.
 */
@Repository
public interface ComplianceCaseRepository extends BaseRepository<ComplianceCase, Long> {

    /**
     * Find compliance cases by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByPartyId(Long partyId);

    /**
     * Find compliance cases by case type.
     *
     * @param caseTypeEnum The type of case
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByCaseType(CaseTypeEnum caseTypeEnum);

    /**
     * Find compliance cases by case status.
     *
     * @param caseStatusEnum The status of the case
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByCaseStatus(CaseStatusEnum caseStatusEnum);

    /**
     * Find compliance cases by case priority.
     *
     * @param casePriorityEnum The priority of the case
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByCasePriority(CasePriorityEnum casePriorityEnum);

    /**
     * Find compliance cases by assigned user.
     *
     * @param assignedTo The user assigned to the case
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByAssignedTo(String assignedTo);

    /**
     * Find compliance cases with due date before a specified date.
     *
     * @param dueDate The due date threshold
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByDueDateBefore(LocalDateTime dueDate);

    /**
     * Find compliance cases that require SEPBLAC reporting.
     *
     * @param reportRequired Whether SEPBLAC reporting is required
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByReportToSepblacRequired(Boolean reportRequired);

    /**
     * Find compliance cases by resolution date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByResolutionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find compliance cases by party ID and case status.
     *
     * @param partyId The ID of the party
     * @param caseStatusEnum The status of the case
     * @return A flux of compliance cases
     */
    Flux<ComplianceCase> findByPartyIdAndCaseStatus(Long partyId, CaseStatusEnum caseStatusEnum);

    /**
     * Find the latest compliance case for a party.
     *
     * @param partyId The ID of the party
     * @return A mono with the latest compliance case
     */
    Mono<ComplianceCase> findFirstByPartyIdOrderByDateCreatedDesc(Long partyId);
}