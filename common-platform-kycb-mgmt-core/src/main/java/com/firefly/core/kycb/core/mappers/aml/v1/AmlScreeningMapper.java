package com.firefly.core.kycb.core.mappers.aml.v1;

import com.firefly.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import com.firefly.core.kycb.models.entities.aml.v1.AmlScreening;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between AmlScreening entity and AmlScreeningDTO.
 */
@Mapper(componentModel = "spring")
public interface AmlScreeningMapper {
    AmlScreeningDTO toDTO(AmlScreening entity);
    AmlScreening toEntity(AmlScreeningDTO dto);
}