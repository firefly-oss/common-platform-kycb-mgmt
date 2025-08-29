package com.firefly.core.kycb.core.mappers.risk.v1;

import com.firefly.core.kycb.interfaces.dtos.risk.v1.RiskAssessmentDTO;
import com.firefly.core.kycb.models.entities.risk.v1.RiskAssessment;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between RiskAssessment entity and RiskAssessmentDTO.
 */
@Mapper(componentModel = "spring")
public interface RiskAssessmentMapper {
    RiskAssessmentDTO toDTO(RiskAssessment entity);
    RiskAssessment toEntity(RiskAssessmentDTO dto);
}