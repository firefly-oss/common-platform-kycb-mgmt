package com.catalis.core.kycb.core.mappers.kyb.v1;

import com.catalis.core.kycb.interfaces.dtos.kyb.v1.KybVerificationDTO;
import com.catalis.core.kycb.models.entities.kyb.v1.KybVerification;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between KybVerification entity and KybVerificationDTO.
 */
@Mapper(componentModel = "spring")
public interface KybVerificationMapper {
    KybVerificationDTO toDTO(KybVerification entity);
    KybVerification toEntity(KybVerificationDTO dto);
}