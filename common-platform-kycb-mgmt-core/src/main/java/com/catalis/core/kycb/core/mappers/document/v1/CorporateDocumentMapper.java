package com.catalis.core.kycb.core.mappers.document.v1;

import com.catalis.core.kycb.interfaces.dtos.document.v1.CorporateDocumentDTO;
import com.catalis.core.kycb.models.entities.document.v1.CorporateDocument;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between CorporateDocument entity and CorporateDocumentDTO.
 */
@Mapper(componentModel = "spring")
public interface CorporateDocumentMapper {
    CorporateDocumentDTO toDTO(CorporateDocument entity);
    CorporateDocument toEntity(CorporateDocumentDTO dto);
}