package com.catalis.core.kycb.core.mappers.economic.v1;

import com.catalis.core.kycb.interfaces.dtos.economic.v1.EconomicActivityDTO;
import com.catalis.core.kycb.models.entities.economic.v1.EconomicActivity;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between EconomicActivity entity and EconomicActivityDTO.
 */
@Mapper(componentModel = "spring")
public interface EconomicActivityMapper {
    EconomicActivityDTO toDTO(EconomicActivity entity);
    EconomicActivity toEntity(EconomicActivityDTO dto);
}