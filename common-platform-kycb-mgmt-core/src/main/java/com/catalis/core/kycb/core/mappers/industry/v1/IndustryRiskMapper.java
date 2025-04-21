package com.catalis.core.kycb.core.mappers.industry.v1;

import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import com.catalis.core.kycb.models.entities.industry.v1.IndustryRisk;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between IndustryRisk entity and IndustryRiskDTO.
 */
@Mapper(componentModel = "spring")
public interface IndustryRiskMapper {
    IndustryRiskDTO toDTO(IndustryRisk entity);
    IndustryRisk toEntity(IndustryRiskDTO dto);
}