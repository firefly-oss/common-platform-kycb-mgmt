package com.firefly.core.kycb.interfaces.dtos.sanctions.v1;

import com.firefly.annotations.ValidDateTime;
import com.firefly.core.kycb.interfaces.dtos.BaseDTO;
import com.firefly.core.kycb.interfaces.enums.sanctions.v1.EntitySanctionsQuestionnaireTypeEnum;
import com.firefly.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.UUID;

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
    private UUID sanctionsQuestionnaireId;

    @FilterableId
    @NotNull(message = "Party ID is required")
    private UUID partyId;

    /**
     * Defines for which part of the entity the user answers the questionnaire.
     */
    @NotNull(message = "Entity sanctions questionnaire type is required")
    private EntitySanctionsQuestionnaireTypeEnum entitySanctionsQuestionnaire;

    /**
     * Whether the entity and its business units, subsidiaries, and joint ventures have a commercial activity outside the European Union.
     */
    @NotNull(message = "Activity outside EU flag is required")
    private Boolean activityOutsideEu;

    /**
     * Whether the entity, its subsidiaries, entities, employees, directors, beneficial owners, or joint ventures are subject to Economic Sanctions.
     */
    @NotNull(message = "Economic sanctions flag is required")
    private Boolean economicSanctions;

    /**
     * Whether the entity, its subsidiaries, or joint ventures, located within or operating from any countries or territories are subject to Comprehensive Sanctions.
     */
    @NotNull(message = "Resident countries sanctions flag is required")
    private Boolean residentCountriesSanctions;

    /**
     * Whether the entity, its subsidiaries, or joint ventures, are engaged in transactions, investments, business, or other dealings that directly or indirectly involve or benefit:
     * Any countries or territories subject to Comprehensive Sanctions, or
     * Any person or entity which is the target of any Sanctions ("Sanctioned Targets").
     */
    @NotNull(message = "Involved sanctions flag is required")
    private Boolean involvedSanctions;

    /**
     * The date when the questionnaire was completed.
     */
    @NotNull(message = "Questionnaire date is required")
    @ValidDateTime
    private LocalDateTime questionnaireDate;
}
