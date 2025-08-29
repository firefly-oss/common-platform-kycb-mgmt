package com.firefly.core.kycb.core.mappers.compliance.v1;

import com.firefly.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
import com.firefly.core.kycb.models.entities.compliance.v1.ComplianceCase;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between ComplianceCase entity and ComplianceCaseDTO.
 */
@Mapper(componentModel = "spring")
public interface ComplianceCaseMapper {
    ComplianceCaseDTO toDTO(ComplianceCase entity);
    ComplianceCase toEntity(ComplianceCaseDTO dto);
}