package com.firefly.core.kycb.core.mappers.industry.v1;

import com.firefly.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import com.firefly.core.kycb.models.entities.industry.v1.IndustryRisk;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between IndustryRisk entity and IndustryRiskDTO.
 */
@Mapper(componentModel = "spring")
public interface IndustryRiskMapper {
    IndustryRiskDTO toDTO(IndustryRisk entity);
    IndustryRisk toEntity(IndustryRiskDTO dto);
}