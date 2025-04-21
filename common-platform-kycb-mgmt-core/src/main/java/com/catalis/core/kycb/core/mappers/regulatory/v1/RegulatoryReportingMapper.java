package com.catalis.core.kycb.core.mappers.regulatory.v1;

import com.catalis.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
import com.catalis.core.kycb.models.entities.regulatory.v1.RegulatoryReporting;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between RegulatoryReporting entity and RegulatoryReportingDTO.
 */
@Mapper(componentModel = "spring")
public interface RegulatoryReportingMapper {
    RegulatoryReportingDTO toDTO(RegulatoryReporting entity);
    RegulatoryReporting toEntity(RegulatoryReportingDTO dto);
}