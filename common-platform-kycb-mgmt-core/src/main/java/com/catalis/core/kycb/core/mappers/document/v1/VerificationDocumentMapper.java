package com.catalis.core.kycb.core.mappers.document.v1;

import com.catalis.core.kycb.interfaces.dtos.document.v1.VerificationDocumentDTO;
import com.catalis.core.kycb.models.entities.document.v1.VerificationDocument;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between VerificationDocument entity and VerificationDocumentDTO.
 */
@Mapper(componentModel = "spring")
public interface VerificationDocumentMapper {
    VerificationDocumentDTO toDTO(VerificationDocument entity);
    VerificationDocument toEntity(VerificationDocumentDTO dto);
}