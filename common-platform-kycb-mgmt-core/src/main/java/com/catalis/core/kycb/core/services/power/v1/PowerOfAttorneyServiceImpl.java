package com.catalis.core.kycb.core.services.power.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.power.v1.PowerOfAttorneyMapper;
import com.catalis.core.kycb.interfaces.dtos.power.v1.PowerOfAttorneyDTO;
import com.catalis.core.kycb.interfaces.enums.power.v1.PowerTypeEnum;
import com.catalis.core.kycb.models.entities.power.v1.PowerOfAttorney;
import com.catalis.core.kycb.models.repositories.power.v1.PowerOfAttorneyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the power of attorney service.
 */
@Service
@Transactional
public class PowerOfAttorneyServiceImpl implements PowerOfAttorneyService {

    @Autowired
    private PowerOfAttorneyRepository repository;

    @Autowired
    private PowerOfAttorneyMapper mapper;

    @Override
    public Mono<PaginationResponse<PowerOfAttorneyDTO>> findAll(FilterRequest<PowerOfAttorneyDTO> filterRequest) {
        return FilterUtils.createFilter(
                PowerOfAttorney.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Flux<PowerOfAttorneyDTO> findByPartyId(Long partyId) {
        return repository.findByPartyId(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<PowerOfAttorneyDTO> findByDocumentId(Long documentId) {
        return repository.findByCorporateDocumentId(documentId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PowerOfAttorneyDTO> create(PowerOfAttorneyDTO dto) {
        PowerOfAttorney entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PowerOfAttorneyDTO> getById(Long powerId) {
        return repository.findById(powerId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PowerOfAttorneyDTO> update(Long powerId, PowerOfAttorneyDTO dto) {
        return repository.findById(powerId)
                .flatMap(existingEntity -> {
                    PowerOfAttorney updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setPowerOfAttorneyId(powerId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    // Preserve verification data if not changing
                    if (existingEntity.getIsVerified() && updatedEntity.getIsVerified() == null) {
                        updatedEntity.setIsVerified(existingEntity.getIsVerified());
                        updatedEntity.setVerificationDate(existingEntity.getVerificationDate());
                        updatedEntity.setVerificationMethod(existingEntity.getVerificationMethod());
                        updatedEntity.setVerifyingLegalCounsel(existingEntity.getVerifyingLegalCounsel());
                    }
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long powerId) {
        return repository.deleteById(powerId);
    }

    @Override
    public Flux<PowerOfAttorneyDTO> findByPowerType(String powerType) {
        return repository.findByPowerType(PowerTypeEnum.valueOf(powerType))
                .map(mapper::toDTO);
    }

    @Override
    public Mono<PowerOfAttorneyDTO> verifyPower(Long powerId, String verificationNotes) {
        return repository.findById(powerId)
                .flatMap(entity -> {
                    entity.setIsVerified(true);
                    entity.setVerificationDate(LocalDateTime.now());
                    // In a real implementation, we would set the verification method and legal counsel
                    entity.setVerificationMethod("Manual Verification");
                    entity.setVerifyingLegalCounsel(verificationNotes);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }
}
