package com.catalis.core.kycb.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.aml.v1.AmlMatchMapper;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlMatchDTO;
import com.catalis.core.kycb.models.entities.aml.v1.AmlMatch;
import com.catalis.core.kycb.models.repositories.aml.v1.AmlMatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

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
    public Mono<AmlMatchDTO> getById(Long amlMatchId) {
        return repository.findById(amlMatchId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlMatchDTO> update(Long amlMatchId, AmlMatchDTO dto) {
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
    public Mono<Void> delete(Long amlMatchId) {
        return repository.deleteById(amlMatchId);
    }
}
