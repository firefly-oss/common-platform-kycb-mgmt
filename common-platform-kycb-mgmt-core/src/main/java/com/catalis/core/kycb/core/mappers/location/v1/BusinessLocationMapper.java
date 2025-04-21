package com.catalis.core.kycb.core.mappers.location.v1;

import com.catalis.core.kycb.interfaces.dtos.location.v1.BusinessLocationDTO;
import com.catalis.core.kycb.models.entities.location.v1.BusinessLocation;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between BusinessLocation entity and BusinessLocationDTO.
 */
@Mapper(componentModel = "spring")
public interface BusinessLocationMapper {
    BusinessLocationDTO toDTO(BusinessLocation entity);
    BusinessLocation toEntity(BusinessLocationDTO dto);
}