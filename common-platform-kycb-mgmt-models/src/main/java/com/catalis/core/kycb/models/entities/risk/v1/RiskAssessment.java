package com.catalis.core.kycb.models.entities.risk.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.assessment.v1.AssessmentTypeEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskCategoryEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a risk assessment.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("risk_assessment")
public class RiskAssessment extends BaseEntity {

    @Id
    @Column("risk_assessment_id")
    private Long riskAssessmentId;

    @Column("party_id")
    private Long partyId;

    @Column("assessment_type")
    private AssessmentTypeEnum assessmentType;

    @Column("assessment_date")
    private LocalDateTime assessmentDate;

    @Column("risk_category")
    private RiskCategoryEnum riskCategory;

    @Column("risk_score")
    private Integer riskScore;

    @Column("risk_level")
    private RiskLevelEnum riskLevel;

    @Column("risk_factors")
    private String riskFactors;

    @Column("assessment_notes")
    private String assessmentNotes;

    @Column("assessment_agent")
    private String assessmentAgent;

    @Column("next_assessment_date")
    private LocalDateTime nextAssessmentDate;
}