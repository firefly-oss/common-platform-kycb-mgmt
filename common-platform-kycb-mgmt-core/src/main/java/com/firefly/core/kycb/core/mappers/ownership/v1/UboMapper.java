package com.firefly.core.kycb.core.mappers.ownership.v1;

import com.firefly.core.kycb.interfaces.dtos.ownership.v1.UboDTO;
import com.firefly.core.kycb.models.entities.ownership.v1.Ubo;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between Ubo entity and UboDTO.
 */
@Mapper(componentModel = "spring")
public interface UboMapper {
    UboDTO toDTO(Ubo entity);
    Ubo toEntity(UboDTO dto);
}