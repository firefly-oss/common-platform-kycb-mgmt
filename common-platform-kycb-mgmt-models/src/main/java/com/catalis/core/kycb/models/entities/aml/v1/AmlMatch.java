package com.catalis.core.kycb.models.entities.aml.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.aml.v1.ListTypeEnum;
import com.catalis.core.kycb.interfaces.enums.resolution.v1.ResolutionStatusEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a match from an AML screening.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("aml_match")
public class AmlMatch extends BaseEntity {

    @Id
    @Column("aml_match_id")
    private Long amlMatchId;

    @Column("aml_screening_id")
    private Long amlScreeningId;

    @Column("list_type")
    private ListTypeEnum listType;

    @Column("list_source")
    private String listSource;

    @Column("matched_name")
    private String matchedName;

    @Column("match_score")
    private BigDecimal matchScore;

    @Column("match_details")
    private String matchDetails;

    @Column("resolution_status")
    private ResolutionStatusEnum resolutionStatus;

    @Column("resolution_notes")
    private String resolutionNotes;

    @Column("resolution_agent")
    private String resolutionAgent;

    @Column("resolution_date")
    private LocalDateTime resolutionDate;
}