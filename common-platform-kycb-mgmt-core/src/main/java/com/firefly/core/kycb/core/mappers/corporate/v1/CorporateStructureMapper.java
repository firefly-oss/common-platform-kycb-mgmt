package com.firefly.core.kycb.core.mappers.corporate.v1;

import com.firefly.core.kycb.interfaces.dtos.corporate.v1.CorporateStructureDTO;
import com.firefly.core.kycb.models.entities.corporate.v1.CorporateStructure;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between CorporateStructure entity and CorporateStructureDTO.
 */
@Mapper(componentModel = "spring")
public interface CorporateStructureMapper {
    CorporateStructureDTO toDTO(CorporateStructure entity);
    CorporateStructure toEntity(CorporateStructureDTO dto);
}