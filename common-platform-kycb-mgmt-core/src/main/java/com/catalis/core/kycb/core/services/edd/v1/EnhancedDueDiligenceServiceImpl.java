package com.catalis.core.kycb.core.services.edd.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.edd.v1.EnhancedDueDiligenceMapper;
import com.catalis.core.kycb.interfaces.dtos.edd.v1.EnhancedDueDiligenceDTO;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddReasonEnum;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddStatusEnum;
import com.catalis.core.kycb.models.entities.edd.v1.EnhancedDueDiligence;
import com.catalis.core.kycb.models.repositories.edd.v1.EnhancedDueDiligenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the enhanced due diligence service.
 */
@Service
@Transactional
public class EnhancedDueDiligenceServiceImpl implements EnhancedDueDiligenceService {

    @Autowired
    private EnhancedDueDiligenceRepository repository;

    @Autowired
    private EnhancedDueDiligenceMapper mapper;

    @Override
    public Mono<PaginationResponse<EnhancedDueDiligenceDTO>> findAll(FilterRequest<EnhancedDueDiligenceDTO> filterRequest) {
        return FilterUtils.createFilter(
                EnhancedDueDiligence.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByKycVerificationId(Long kycVerificationId) {
        return repository.findByKycVerificationId(kycVerificationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> create(EnhancedDueDiligenceDTO dto) {
        EnhancedDueDiligence entity = mapper.toEntity(dto);
        // Set default status to PENDING if not provided
        if (entity.getEddStatus() == null) {
            entity.setEddStatus(EddStatusEnum.PENDING);
        }
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> getById(Long eddId) {
        return repository.findById(eddId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> update(Long eddId, EnhancedDueDiligenceDTO dto) {
        return repository.findById(eddId)
                .flatMap(existingEntity -> {
                    EnhancedDueDiligence updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setEddId(eddId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long eddId) {
        return repository.deleteById(eddId);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByEddReason(String eddReason) {
        return repository.findByEddReason(EddReasonEnum.valueOf(eddReason))
                .map(mapper::toDTO);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByEddStatus(String eddStatus) {
        return repository.findByEddStatus(EddStatusEnum.valueOf(eddStatus))
                .map(mapper::toDTO);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByApprovingAuthority(String approvingAuthority) {
        return repository.findByApprovingAuthority(approvingAuthority)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByInternalCommitteeApproval(Boolean internalCommitteeApproval) {
        return repository.findByInternalCommitteeApproval(internalCommitteeApproval)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByApprovalDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByApprovalDateBetween(startDate, endDate)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByCompletionDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByCompletionDateBetween(startDate, endDate)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<EnhancedDueDiligenceDTO> findByCompletedBy(String completedBy) {
        return repository.findByCompletedBy(completedBy)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> getLatestByKycVerificationId(Long kycVerificationId) {
        return repository.findFirstByKycVerificationIdOrderByDateCreatedDesc(kycVerificationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> completeEdd(Long eddId, String completedBy, String completionNotes) {
        return repository.findById(eddId)
                .flatMap(entity -> {
                    entity.setEddStatus(EddStatusEnum.COMPLETED);
                    entity.setCompletionDate(LocalDateTime.now());
                    entity.setCompletedBy(completedBy);
                    entity.setEddNotes(completionNotes);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> approveEdd(Long eddId, String approvingAuthority, String approvalNotes) {
        return repository.findById(eddId)
                .flatMap(entity -> {
                    entity.setEddStatus(EddStatusEnum.IN_PROGRESS);
                    entity.setApprovingAuthority(approvingAuthority);
                    entity.setApprovalDate(LocalDateTime.now());
                    entity.setEddNotes(approvalNotes);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EnhancedDueDiligenceDTO> waiveEdd(Long eddId, String waiveReason, String waiveAuthority) {
        return repository.findById(eddId)
                .flatMap(entity -> {
                    entity.setEddStatus(EddStatusEnum.WAIVED);
                    entity.setApprovingAuthority(waiveAuthority);
                    entity.setEddNotes(waiveReason);
                    entity.setCompletionDate(LocalDateTime.now());
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }
}