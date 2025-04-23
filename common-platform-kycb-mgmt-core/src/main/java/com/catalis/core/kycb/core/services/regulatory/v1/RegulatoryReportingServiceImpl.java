package com.catalis.core.kycb.core.services.regulatory.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.regulatory.v1.RegulatoryReportingMapper;
import com.catalis.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportStatusEnum;
import com.catalis.core.kycb.models.entities.regulatory.v1.RegulatoryReporting;
import com.catalis.core.kycb.models.repositories.regulatory.v1.RegulatoryReportingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

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
    public Mono<RegulatoryReportingDTO> getById(Long reportId) {
        return repository.findById(reportId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> update(Long reportId, RegulatoryReportingDTO dto) {
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
    public Mono<Void> delete(Long reportId) {
        return repository.deleteById(reportId);
    }
}
