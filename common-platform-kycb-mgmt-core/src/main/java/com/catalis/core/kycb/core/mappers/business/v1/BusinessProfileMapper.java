package com.catalis.core.kycb.core.mappers.business.v1;

import com.catalis.core.kycb.interfaces.dtos.business.v1.BusinessProfileDTO;
import com.catalis.core.kycb.models.entities.business.v1.BusinessProfile;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between BusinessProfile entity and BusinessProfileDTO.
 */
@Mapper(componentModel = "spring")
public interface BusinessProfileMapper {
    BusinessProfileDTO toDTO(BusinessProfile entity);
    BusinessProfile toEntity(BusinessProfileDTO dto);
}