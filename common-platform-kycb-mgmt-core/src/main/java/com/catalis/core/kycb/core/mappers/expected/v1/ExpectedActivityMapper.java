package com.catalis.core.kycb.core.mappers.expected.v1;

import com.catalis.core.kycb.interfaces.dtos.expected.v1.ExpectedActivityDTO;
import com.catalis.core.kycb.models.entities.expected.v1.ExpectedActivity;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between ExpectedActivity entity and ExpectedActivityDTO.
 */
@Mapper(componentModel = "spring")
public interface ExpectedActivityMapper {
    ExpectedActivityDTO toDTO(ExpectedActivity entity);
    ExpectedActivity toEntity(ExpectedActivityDTO dto);
}