package com.firefly.core.kycb.core.mappers.expected.v1;

import com.firefly.core.kycb.interfaces.dtos.expected.v1.ExpectedActivityDTO;
import com.firefly.core.kycb.models.entities.expected.v1.ExpectedActivity;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between ExpectedActivity entity and ExpectedActivityDTO.
 */
@Mapper(componentModel = "spring")
public interface ExpectedActivityMapper {
    ExpectedActivityDTO toDTO(ExpectedActivity entity);
    ExpectedActivity toEntity(ExpectedActivityDTO dto);
}