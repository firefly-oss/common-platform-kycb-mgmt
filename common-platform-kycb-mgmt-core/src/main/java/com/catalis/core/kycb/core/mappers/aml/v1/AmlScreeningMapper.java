package com.catalis.core.kycb.core.mappers.aml.v1;

import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import com.catalis.core.kycb.models.entities.aml.v1.AmlScreening;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between AmlScreening entity and AmlScreeningDTO.
 */
@Mapper(componentModel = "spring")
public interface AmlScreeningMapper {
    AmlScreeningDTO toDTO(AmlScreening entity);
    AmlScreening toEntity(AmlScreeningDTO dto);
}