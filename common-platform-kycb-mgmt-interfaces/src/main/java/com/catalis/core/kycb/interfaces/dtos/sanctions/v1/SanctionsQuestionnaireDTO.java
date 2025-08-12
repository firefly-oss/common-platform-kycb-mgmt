package com.catalis.core.kycb.interfaces.dtos.sanctions.v1;

import com.catalis.annotations.ValidDateTime;
import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.kycb.interfaces.enums.sanctions.v1.EntitySanctionsQuestionnaireTypeEnum;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for sanctions and embargo questionnaire.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class SanctionsQuestionnaireDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Long sanctionsQuestionnaireId;

    @FilterableId
    private Long partyId;

    /**
     * Defines for which part of the entity the user answers the questionnaire.
     */
    private EntitySanctionsQuestionnaireTypeEnum entitySanctionsQuestionnaire;

    /**
     * Whether the entity and its business units, subsidiaries, and joint ventures have a commercial activity outside the European Union.
     */
    private Boolean activityOutsideEu;

    /**
     * Whether the entity, its subsidiaries, entities, employees, directors, beneficial owners, or joint ventures are subject to Economic Sanctions.
     */
    private Boolean economicSanctions;

    /**
     * Whether the entity, its subsidiaries, or joint ventures, located within or operating from any countries or territories are subject to Comprehensive Sanctions.
     */
    private Boolean residentCountriesSanctions;

    /**
     * Whether the entity, its subsidiaries, or joint ventures, are engaged in transactions, investments, business, or other dealings that directly or indirectly involve or benefit:
     * Any countries or territories subject to Comprehensive Sanctions, or
     * Any person or entity which is the target of any Sanctions ("Sanctioned Targets").
     */
    private Boolean involvedSanctions;

    /**
     * The date when the questionnaire was completed.
     */
    @ValidDateTime
    private LocalDateTime questionnaireDate;
}
