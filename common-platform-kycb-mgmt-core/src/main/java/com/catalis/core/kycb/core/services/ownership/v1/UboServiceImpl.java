package com.catalis.core.kycb.core.services.ownership.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.ownership.v1.UboMapper;
import com.catalis.core.kycb.interfaces.dtos.ownership.v1.UboDTO;
import com.catalis.core.kycb.interfaces.enums.ownership.v1.OwnershipTypeEnum;
import com.catalis.core.kycb.models.entities.ownership.v1.Ubo;
import com.catalis.core.kycb.models.repositories.ownership.v1.UboRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Implementation of the UBO service.
 */
@Service
@Transactional
public class UboServiceImpl implements UboService {

    @Autowired
    private UboRepository repository;

    @Autowired
    private UboMapper mapper;

    @Override
    public Mono<PaginationResponse<UboDTO>> findAll(FilterRequest<UboDTO> filterRequest) {
        return FilterUtils.createFilter(
                Ubo.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<UboDTO> create(UboDTO dto) {
        Ubo entity = mapper.toEntity(dto);
        // Set start date to now if not provided
        if (entity.getStartDate() == null) {
            entity.setStartDate(LocalDateTime.now());
        }
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<UboDTO> getById(Long uboId) {
        return repository.findById(uboId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<UboDTO> update(Long uboId, UboDTO dto) {
        return repository.findById(uboId)
                .flatMap(existingEntity -> {
                    Ubo updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setUboId(uboId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    // Preserve verification data if not changing
                    if (existingEntity.getIsVerified() && updatedEntity.getIsVerified() == null) {
                        updatedEntity.setIsVerified(existingEntity.getIsVerified());
                        updatedEntity.setVerificationDate(existingEntity.getVerificationDate());
                        updatedEntity.setVerificationMethod(existingEntity.getVerificationMethod());
                        updatedEntity.setTitularidadRealDocument(existingEntity.getTitularidadRealDocument());
                    }
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long uboId) {
        return repository.deleteById(uboId);
    }
}