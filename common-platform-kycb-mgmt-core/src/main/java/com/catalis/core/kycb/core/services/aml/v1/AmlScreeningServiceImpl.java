package com.catalis.core.kycb.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.aml.v1.AmlScreeningMapper;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import com.catalis.core.kycb.models.entities.aml.v1.AmlScreening;
import com.catalis.core.kycb.models.repositories.aml.v1.AmlScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the AML screening service.
 */
@Service
@Transactional
public class AmlScreeningServiceImpl implements AmlScreeningService {

    @Autowired
    private AmlScreeningRepository repository;

    @Autowired
    private AmlScreeningMapper mapper;

    @Override
    public Mono<PaginationResponse<AmlScreeningDTO>> findAll(FilterRequest<AmlScreeningDTO> filterRequest) {
        return FilterUtils.createFilter(
                AmlScreening.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<AmlScreeningDTO> create(AmlScreeningDTO dto) {
        AmlScreening entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> getById(Long amlScreeningId) {
        return repository.findById(amlScreeningId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> update(Long amlScreeningId, AmlScreeningDTO dto) {
        return repository.findById(amlScreeningId)
                .flatMap(existingEntity -> {
                    AmlScreening updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAmlScreeningId(amlScreeningId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long amlScreeningId) {
        return repository.deleteById(amlScreeningId);
    }
}
