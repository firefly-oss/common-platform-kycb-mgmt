package com.firefly.core.kycb.core.mappers.aml.v1;

import com.firefly.core.kycb.interfaces.dtos.aml.v1.AmlMatchDTO;
import com.firefly.core.kycb.models.entities.aml.v1.AmlMatch;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between AmlMatch entity and AmlMatchDTO.
 */
@Mapper(componentModel = "spring")
public interface AmlMatchMapper {
    AmlMatchDTO toDTO(AmlMatch entity);
    AmlMatch toEntity(AmlMatchDTO dto);
}