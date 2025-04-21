package com.catalis.core.kycb.core.mappers.power.v1;

import com.catalis.core.kycb.interfaces.dtos.power.v1.PowerOfAttorneyDTO;
import com.catalis.core.kycb.models.entities.power.v1.PowerOfAttorney;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between PowerOfAttorney entity and PowerOfAttorneyDTO.
 */
@Mapper(componentModel = "spring")
public interface PowerOfAttorneyMapper {
    PowerOfAttorneyDTO toDTO(PowerOfAttorney entity);
    PowerOfAttorney toEntity(PowerOfAttorneyDTO dto);
}