package com.catalis.core.kycb.core.mappers.sanctions.v1;

import com.catalis.core.kycb.interfaces.dtos.sanctions.v1.SanctionsQuestionnaireDTO;
import com.catalis.core.kycb.models.entities.sanctions.v1.SanctionsQuestionnaire;
import org.springframework.stereotype.Component;

/**
 * Mapper for converting between SanctionsQuestionnaire entity and DTO.
 */
@Component
public class SanctionsQuestionnaireMapper {

    /**
     * Convert entity to DTO.
     *
     * @param entity The entity to convert
     * @return The converted DTO
     */
    public SanctionsQuestionnaireDTO toDTO(SanctionsQuestionnaire entity) {
        if (entity == null) {
            return null;
        }

        return SanctionsQuestionnaireDTO.builder()
                .sanctionsQuestionnaireId(entity.getSanctionsQuestionnaireId())
                .partyId(entity.getPartyId())
                .entitySanctionsQuestionnaire(entity.getEntitySanctionsQuestionnaire())
                .activityOutsideEu(entity.getActivityOutsideEu())
                .economicSanctions(entity.getEconomicSanctions())
                .residentCountriesSanctions(entity.getResidentCountriesSanctions())
                .involvedSanctions(entity.getInvolvedSanctions())
                .questionnaireDate(entity.getQuestionnaireDate())
                .build();
    }

    /**
     * Convert DTO to entity.
     *
     * @param dto The DTO to convert
     * @return The converted entity
     */
    public SanctionsQuestionnaire toEntity(SanctionsQuestionnaireDTO dto) {
        if (dto == null) {
            return null;
        }

        return SanctionsQuestionnaire.builder()
                .sanctionsQuestionnaireId(dto.getSanctionsQuestionnaireId())
                .partyId(dto.getPartyId())
                .entitySanctionsQuestionnaire(dto.getEntitySanctionsQuestionnaire())
                .activityOutsideEu(dto.getActivityOutsideEu())
                .economicSanctions(dto.getEconomicSanctions())
                .residentCountriesSanctions(dto.getResidentCountriesSanctions())
                .involvedSanctions(dto.getInvolvedSanctions())
                .questionnaireDate(dto.getQuestionnaireDate())
                .build();
    }
}