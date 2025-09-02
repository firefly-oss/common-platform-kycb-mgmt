package com.firefly.core.kycb.core.services.aml.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.core.mappers.aml.v1.AmlMatchMapper;
import com.firefly.core.kycb.interfaces.dtos.aml.v1.AmlMatchDTO;
import com.firefly.core.kycb.models.entities.aml.v1.AmlMatch;
import com.firefly.core.kycb.models.repositories.aml.v1.AmlMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

/**
 * Implementation of the AML match service.
 */
@Service
@Transactional
public class AmlMatchServiceImpl implements AmlMatchService {

    @Autowired
    private AmlMatchRepository repository;

    @Autowired
    private AmlMatchMapper mapper;

    @Override
    public Mono<PaginationResponse<AmlMatchDTO>> findAll(FilterRequest<AmlMatchDTO> filterRequest) {
        return FilterUtils.createFilter(
                AmlMatch.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<AmlMatchDTO> create(AmlMatchDTO dto) {
        AmlMatch entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlMatchDTO> getById(UUID amlMatchId) {
        return repository.findById(amlMatchId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlMatchDTO> update(UUID amlMatchId, AmlMatchDTO dto) {
        return repository.findById(amlMatchId)
                .flatMap(existingEntity -> {
                    AmlMatch updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setAmlMatchId(amlMatchId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(UUID amlMatchId) {
        return repository.deleteById(amlMatchId);
    }
}
