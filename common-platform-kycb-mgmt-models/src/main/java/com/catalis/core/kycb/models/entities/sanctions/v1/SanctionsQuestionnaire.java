package com.catalis.core.kycb.models.entities.sanctions.v1;

import com.catalis.core.kycb.interfaces.enums.sanctions.v1.EntitySanctionsQuestionnaireTypeEnum;
import com.catalis.core.kycb.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a sanctions and embargo questionnaire.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("sanctions_questionnaire")
public class SanctionsQuestionnaire extends BaseEntity {

    @Id
    @Column("sanctions_questionnaire_id")
    private Long sanctionsQuestionnaireId;

    @Column("party_id")
    private Long partyId;

    /**
     * Defines for which part of the entity the user answers the questionnaire.
     */
    @Column("entity_sanctions_questionnaire")
    private EntitySanctionsQuestionnaireTypeEnum entitySanctionsQuestionnaire;

    /**
     * Whether the entity and its business units, subsidiaries, and joint ventures have a commercial activity outside the European Union.
     */
    @Column("activity_outside_eu")
    private Boolean activityOutsideEu;

    /**
     * Whether the entity, its subsidiaries, entities, employees, directors, beneficial owners, or joint ventures are subject to Economic Sanctions.
     */
    @Column("economic_sanctions")
    private Boolean economicSanctions;

    /**
     * Whether the entity, its subsidiaries, or joint ventures, located within or operating from any countries or territories are subject to Comprehensive Sanctions.
     */
    @Column("resident_countries_sanctions")
    private Boolean residentCountriesSanctions;

    /**
     * Whether the entity, its subsidiaries, or joint ventures, are engaged in transactions, investments, business, or other dealings that directly or indirectly involve or benefit:
     * Any countries or territories subject to Comprehensive Sanctions, or
     * Any person or entity which is the target of any Sanctions ("Sanctioned Targets").
     */
    @Column("involved_sanctions")
    private Boolean involvedSanctions;

    /**
     * The date when the questionnaire was completed.
     */
    @Column("questionnaire_date")
    private LocalDateTime questionnaireDate;
}
