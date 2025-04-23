package com.catalis.core.kycb.core.services.kyc.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationRequest;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.kyc.v1.KycVerificationMapper;
import com.catalis.core.kycb.interfaces.dtos.kyc.v1.KycVerificationDTO;
import com.catalis.core.kycb.interfaces.enums.verification.v1.VerificationStatusEnum;
import com.catalis.core.kycb.models.entities.kyc.v1.KycVerification;
import com.catalis.core.kycb.models.repositories.kyc.v1.KycVerificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@Transactional
public class KycVerificationServiceImpl implements KycVerificationService {

    @Autowired
    private KycVerificationRepository repository;

    @Autowired
    private KycVerificationMapper mapper;

    @Override
    public Mono<PaginationResponse<KycVerificationDTO>> findAll(FilterRequest<KycVerificationDTO> filterRequest) {
        return FilterUtils.createFilter(
                KycVerification.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<KycVerificationDTO> create(KycVerificationDTO dto) {
        KycVerification entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<KycVerificationDTO> getById(Long kycVerificationId) {
        return repository.findById(kycVerificationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<KycVerificationDTO> update(Long kycVerificationId, KycVerificationDTO dto) {
        return repository.findById(kycVerificationId)
                .flatMap(existingEntity -> {
                    KycVerification updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setKycVerificationId(kycVerificationId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long kycVerificationId) {
        return repository.deleteById(kycVerificationId);
    }

}
