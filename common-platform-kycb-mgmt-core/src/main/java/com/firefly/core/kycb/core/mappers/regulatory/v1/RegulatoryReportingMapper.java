package com.firefly.core.kycb.core.mappers.regulatory.v1;

import com.firefly.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
import com.firefly.core.kycb.models.entities.regulatory.v1.RegulatoryReporting;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between RegulatoryReporting entity and RegulatoryReportingDTO.
 */
@Mapper(componentModel = "spring")
public interface RegulatoryReportingMapper {
    RegulatoryReportingDTO toDTO(RegulatoryReporting entity);
    RegulatoryReporting toEntity(RegulatoryReportingDTO dto);
}