package com.firefly.core.kycb.models.entities.aml.v1;

import com.firefly.core.kycb.models.entities.BaseEntity;
import com.firefly.core.kycb.interfaces.enums.screening.v1.ScreeningResultEnum;
import com.firefly.core.kycb.interfaces.enums.screening.v1.ScreeningTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Entity representing an AML screening record.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("aml_screening")
public class AmlScreening extends BaseEntity {

    @Id
    @Column("aml_screening_id")
    private UUID amlScreeningId;

    @Column("party_id")
    private UUID partyId;

    @Column("screening_date")
    private LocalDateTime screeningDate;

    @Column("screening_type")
    private ScreeningTypeEnum screeningType;

    @Column("matches_found")
    private Boolean matchesFound;

    @Column("match_count")
    private Integer matchCount;

    @Column("screening_provider")
    private String screeningProvider;

    @Column("reference_id")
    private String referenceId;

    @Column("screening_result")
    private ScreeningResultEnum screeningResult;

    @Column("next_screening_date")
    private LocalDateTime nextScreeningDate;
}