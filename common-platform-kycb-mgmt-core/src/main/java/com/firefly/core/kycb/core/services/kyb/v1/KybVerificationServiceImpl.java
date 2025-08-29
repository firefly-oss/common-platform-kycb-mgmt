package com.firefly.core.kycb.core.services.kyb.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationRequest;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.core.mappers.kyb.v1.KybVerificationMapper;
import com.firefly.core.kycb.interfaces.dtos.kyb.v1.KybVerificationDTO;
import com.firefly.core.kycb.interfaces.enums.verification.v1.VerificationStatusEnum;
import com.firefly.core.kycb.models.entities.kyb.v1.KybVerification;
import com.firefly.core.kycb.models.repositories.kyb.v1.KybVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class KybVerificationServiceImpl implements KybVerificationService {

    @Autowired
    private KybVerificationRepository repository;

    @Autowired
    private KybVerificationMapper mapper;

    @Override
    public Mono<PaginationResponse<KybVerificationDTO>> findAll(FilterRequest<KybVerificationDTO> filterRequest) {
        return FilterUtils.createFilter(
                KybVerification.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<KybVerificationDTO> create(KybVerificationDTO dto) {
        KybVerification entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<KybVerificationDTO> getById(Long kybVerificationId) {
        return repository.findById(kybVerificationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<KybVerificationDTO> update(Long kybVerificationId, KybVerificationDTO dto) {
        return repository.findById(kybVerificationId)
                .flatMap(existingEntity -> {
                    KybVerification updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setKybVerificationId(kybVerificationId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long kybVerificationId) {
        return repository.deleteById(kybVerificationId);
    }

}