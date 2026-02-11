/*
 * Copyright 2025 Firefly Software Solutions Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.kycb.core.mappers.industry.v1;

import com.firefly.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import com.firefly.core.kycb.models.entities.industry.v1.IndustryRisk;
import org.mapstruct.Mapper;

/**
 * Mapper for converting between IndustryRisk entity and IndustryRiskDTO.
 */
@Mapper(componentModel = "spring")
public interface IndustryRiskMapper {
    IndustryRiskDTO toDTO(IndustryRisk entity);
    IndustryRisk toEntity(IndustryRiskDTO dto);
}