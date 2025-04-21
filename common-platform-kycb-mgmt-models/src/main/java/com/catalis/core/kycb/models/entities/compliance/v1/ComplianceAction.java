package com.catalis.core.kycb.models.entities.compliance.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.action.v1.ActionStatusEnum;
import com.catalis.core.kycb.interfaces.enums.action.v1.ActionTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a compliance action within a case.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("compliance_action")
public class ComplianceAction extends BaseEntity {

    @Id
    @Column("compliance_action_id")
    private Long complianceActionId;

    @Column("compliance_case_id")
    private Long complianceCaseId;

    @Column("action_type")
    private ActionTypeEnum actionType;

    @Column("action_status")
    private ActionStatusEnum actionStatus;

    @Column("action_description")
    private String actionDescription;

    @Column("action_agent")
    private String actionAgent;

    @Column("due_date")
    private LocalDateTime dueDate;

    @Column("completion_date")
    private LocalDateTime completionDate;

    @Column("result")
    private String result;
}