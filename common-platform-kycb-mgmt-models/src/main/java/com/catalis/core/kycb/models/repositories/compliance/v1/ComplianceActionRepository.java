package com.catalis.core.kycb.models.repositories.compliance.v1;

import com.catalis.core.kycb.models.entities.compliance.v1.ComplianceAction;
import com.catalis.core.kycb.interfaces.enums.action.v1.ActionStatusEnum;
import com.catalis.core.kycb.interfaces.enums.action.v1.ActionTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Repository for compliance action operations.
 */
@Repository
public interface ComplianceActionRepository extends BaseRepository<ComplianceAction, Long> {

    /**
     * Find compliance actions by compliance case ID.
     *
     * @param complianceCaseId The ID of the compliance case
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByComplianceCaseId(Long complianceCaseId);

    /**
     * Find compliance actions by action type.
     *
     * @param actionType The type of action
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByActionType(ActionTypeEnum actionType);

    /**
     * Find compliance actions by action status.
     *
     * @param actionStatusEnum The status of the action
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByActionStatus(ActionStatusEnum actionStatusEnum);

    /**
     * Find compliance actions by assigned agent.
     *
     * @param actionAgent The agent assigned to the action
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByActionAgent(String actionAgent);

    /**
     * Find compliance actions with due date before a specified date.
     *
     * @param dueDate The due date threshold
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByDueDateBefore(LocalDateTime dueDate);

    /**
     * Find compliance actions by completion date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByCompletionDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find compliance actions by compliance case ID and action status.
     *
     * @param complianceCaseId The ID of the compliance case
     * @param actionStatusEnum The status of the action
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByComplianceCaseIdAndActionStatus(Long complianceCaseId, ActionStatusEnum actionStatusEnum);

    /**
     * Find compliance actions by compliance case ID and action type.
     *
     * @param complianceCaseId The ID of the compliance case
     * @param actionType The type of action
     * @return A flux of compliance actions
     */
    Flux<ComplianceAction> findByComplianceCaseIdAndActionType(Long complianceCaseId, ActionTypeEnum actionType);

    /**
     * Find the latest compliance action for a case.
     *
     * @param complianceCaseId The ID of the compliance case
     * @return A mono with the latest compliance action
     */
    Mono<ComplianceAction> findFirstByComplianceCaseIdOrderByDateCreatedDesc(Long complianceCaseId);
}