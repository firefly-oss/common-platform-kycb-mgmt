package com.catalis.core.kycb.models.entities.industry.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
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
 * Entity representing an industry risk assessment.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("industry_risk")
public class IndustryRisk extends BaseEntity {

    @Id
    @Column("industry_risk_id")
    private Long industryRiskId;

    @Column("activity_code")
    private String activityCode;

    @Column("industry_name")
    private String industryName;

    @Column("inherent_risk_level")
    private RiskLevelEnum inherentRiskLevel;

    @Column("risk_score")
    private Integer riskScore;

    @Column("risk_factors")
    private String riskFactors;

    @Column("mitigating_factors")
    private String mitigatingFactors;

    @Column("sepblac_high_risk")
    private Boolean sepblacHighRisk;

    @Column("eu_high_risk")
    private Boolean euHighRisk;

    @Column("fatf_high_risk")
    private Boolean fatfHighRisk;

    @Column("cash_intensive")
    private Boolean cashIntensive;

    @Column("complex_structures")
    private Boolean complexStructures;

    @Column("assessment_date")
    private LocalDateTime assessmentDate;

    @Column("assessed_by")
    private String assessedBy;

    @Column("next_assessment_date")
    private LocalDateTime nextAssessmentDate;

    @Column("requires_edd")
    private Boolean requiresEdd;
}