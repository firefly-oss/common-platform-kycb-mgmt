package com.firefly.core.kycb.core.mappers.document.v1;

import com.firefly.core.kycb.interfaces.dtos.document.v1.CorporateDocumentDTO;
import com.firefly.core.kycb.models.entities.document.v1.CorporateDocument;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between CorporateDocument entity and CorporateDocumentDTO.
 */
@Mapper(componentModel = "spring")
public interface CorporateDocumentMapper {
    CorporateDocumentDTO toDTO(CorporateDocument entity);
    CorporateDocument toEntity(CorporateDocumentDTO dto);
}