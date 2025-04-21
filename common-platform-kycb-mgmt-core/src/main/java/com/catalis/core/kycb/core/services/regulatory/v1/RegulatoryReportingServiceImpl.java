package com.catalis.core.kycb.core.services.regulatory.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.regulatory.v1.RegulatoryReportingMapper;
import com.catalis.core.kycb.interfaces.dtos.regulatory.v1.RegulatoryReportingDTO;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportStatusEnum;
import com.catalis.core.kycb.interfaces.enums.report.v1.ReportTypeEnum;
import com.catalis.core.kycb.models.entities.regulatory.v1.RegulatoryReporting;
import com.catalis.core.kycb.models.repositories.regulatory.v1.RegulatoryReportingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

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
    public Flux<RegulatoryReportingDTO> findByComplianceCaseId(Long caseId) {
        return repository.findByComplianceCaseId(caseId)
                .map(mapper::toDTO);
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

    @Override
    public Flux<RegulatoryReportingDTO> findByReportType(String reportType) {
        return repository.findByReportType(ReportTypeEnum.valueOf(reportType))
                .map(mapper::toDTO);
    }

    @Override
    public Flux<RegulatoryReportingDTO> findByReportStatus(String reportStatus) {
        return repository.findByReportStatus(ReportStatusEnum.valueOf(reportStatus))
                .map(mapper::toDTO);
    }

    @Override
    public Flux<RegulatoryReportingDTO> findBySubmittedBy(String submittedBy) {
        return repository.findBySubmittingAgent(submittedBy)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<RegulatoryReportingDTO> findBySubmissionDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findBySubmissionDateBetween(startDate, endDate)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<RegulatoryReportingDTO> findByComplianceCaseIdAndReportStatus(Long caseId, String reportStatus) {
        return repository.findByComplianceCaseIdAndReportStatus(caseId, ReportStatusEnum.valueOf(reportStatus))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> getLatestByComplianceCaseId(Long caseId) {
        return repository.findFirstByComplianceCaseIdOrderBySubmissionDateDesc(caseId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> submitReport(Long reportId, String submittedBy, String submissionNotes) {
        return repository.findById(reportId)
                .flatMap(entity -> {
                    entity.setReportStatus(ReportStatusEnum.SUBMITTED);
                    entity.setSubmittingAgent(submittedBy);
                    entity.setSubmissionDate(LocalDateTime.now());
                    entity.setReportContentSummary(submissionNotes);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> approveReport(Long reportId, String approvedBy, String approvalNotes) {
        return repository.findById(reportId)
                .flatMap(entity -> {
                    entity.setReportStatus(ReportStatusEnum.ACKNOWLEDGED);
                    entity.setSubmittingAgent(approvedBy); // Using submittingAgent as a workaround
                    entity.setAcknowledgmentDate(LocalDateTime.now());
                    entity.setReportContentSummary(approvalNotes);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<RegulatoryReportingDTO> rejectReport(Long reportId, String rejectedBy, String rejectionReason) {
        return repository.findById(reportId)
                .flatMap(entity -> {
                    entity.setReportStatus(ReportStatusEnum.SUPPLEMENTED); // Using SUPPLEMENTED as a workaround
                    entity.setSubmittingAgent(rejectedBy); // Using submittingAgent as a workaround
                    entity.setReportContentSummary(rejectionReason);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }
}
