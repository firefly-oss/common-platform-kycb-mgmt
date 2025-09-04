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


package com.firefly.core.kycb.core.services.regulatory.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.core.mappers.regulatory.v1.RegulatoryReportingMapper;
import com.firefly.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
import com.firefly.core.kycb.interfaces.enums.report.v1.ReportStatusEnum;
import com.firefly.core.kycb.models.entities.regulatory.v1.RegulatoryReporting;
import com.firefly.core.kycb.models.repositories.regulatory.v1.RegulatoryReportingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Implementation of the regulatory reporting service.
 */
@Service
@Transactional
public class RegulatoryReportingServiceImpl implements RegulatoryReportingService {

    @Autowired
    private RegulatoryReportingRepository repository;

    @Autowired
    private RegulatoryReportingMapper mapper;

    @Override
    public Mono<PaginationResponse<RegulatoryReportingDTO>> findAll(FilterRequest<RegulatoryReportingDTO> filterRequest) {
        return FilterUtils.createFilter(
                RegulatoryReporting.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<RegulatoryReportingDTO> create(RegulatoryReportingDTO dto) {
        RegulatoryReporting entity = mapper.toEntity(dto);
        // Set default status to DRAFT if not provided
        if (entity.getReportStatus() == null) {
            entity.setReportStatus(ReportStatusEnum.DRAFT);
        }
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> getById(UUID reportId) {
        return repository.findById(reportId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> update(UUID reportId, RegulatoryReportingDTO dto) {
        return repository.findById(reportId)
                .flatMap(existingEntity -> {
                    RegulatoryReporting updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setReportId(reportId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID reportId) {
        return repository.deleteById(reportId);
    }
}
