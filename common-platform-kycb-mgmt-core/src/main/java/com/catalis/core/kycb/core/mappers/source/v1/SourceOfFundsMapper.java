package com.catalis.core.kycb.core.mappers.source.v1;

import com.catalis.core.kycb.interfaces.dtos.source.v1.SourceOfFundsDTO;
import com.catalis.core.kycb.models.entities.source.v1.SourceOfFunds;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between SourceOfFunds entity and SourceOfFundsDTO.
 */
@Mapper(componentModel = "spring")
public interface SourceOfFundsMapper {
    SourceOfFundsDTO toDTO(SourceOfFunds entity);
    SourceOfFunds toEntity(SourceOfFundsDTO dto);
}