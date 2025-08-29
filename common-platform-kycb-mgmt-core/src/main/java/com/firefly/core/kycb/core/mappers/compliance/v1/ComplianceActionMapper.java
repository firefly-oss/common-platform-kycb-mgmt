package com.firefly.core.kycb.core.mappers.compliance.v1;

import com.firefly.core.kycb.interfaces.dtos.compliance.v1.ComplianceActionDTO;
import com.firefly.core.kycb.models.entities.compliance.v1.ComplianceAction;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between ComplianceAction entity and ComplianceActionDTO.
 */
@Mapper(componentModel = "spring")
public interface ComplianceActionMapper {
    ComplianceActionDTO toDTO(ComplianceAction entity);
    ComplianceAction toEntity(ComplianceActionDTO dto);
}