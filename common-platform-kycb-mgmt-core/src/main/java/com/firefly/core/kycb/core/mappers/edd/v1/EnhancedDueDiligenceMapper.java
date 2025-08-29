package com.firefly.core.kycb.core.mappers.edd.v1;

import com.firefly.core.kycb.interfaces.dtos.edd.v1.EnhancedDueDiligenceDTO;
import com.firefly.core.kycb.models.entities.edd.v1.EnhancedDueDiligence;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between EnhancedDueDiligence entity and EnhancedDueDiligenceDTO.
 */
@Mapper(componentModel = "spring")
public interface EnhancedDueDiligenceMapper {
    EnhancedDueDiligenceDTO toDTO(EnhancedDueDiligence entity);
    EnhancedDueDiligence toEntity(EnhancedDueDiligenceDTO dto);
}