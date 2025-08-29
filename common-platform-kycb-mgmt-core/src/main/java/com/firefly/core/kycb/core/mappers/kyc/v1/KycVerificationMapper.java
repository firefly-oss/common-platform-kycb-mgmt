package com.firefly.core.kycb.core.mappers.kyc.v1;

import com.firefly.core.kycb.interfaces.dtos.kyc.v1.KycVerificationDTO;
import com.firefly.core.kycb.models.entities.kyc.v1.KycVerification;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between KycVerification entity and KycVerificationDTO.
 */
@Mapper(componentModel = "spring")
public interface KycVerificationMapper {
    KycVerificationDTO toDTO(KycVerification entity);
    KycVerification toEntity(KycVerificationDTO dto);
}