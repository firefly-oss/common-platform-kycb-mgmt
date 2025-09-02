package com.firefly.core.kycb.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.core.mappers.aml.v1.AmlScreeningMapper;
import com.firefly.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import com.firefly.core.kycb.models.entities.aml.v1.AmlScreening;
import com.firefly.core.kycb.models.repositories.aml.v1.AmlScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

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
    public Mono<AmlScreeningDTO> getById(UUID amlScreeningId) {
        return repository.findById(amlScreeningId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> update(UUID amlScreeningId, AmlScreeningDTO dto) {
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
    public Mono<Void> delete(UUID amlScreeningId) {
        return repository.deleteById(amlScreeningId);
    }
}
