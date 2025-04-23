package com.catalis.core.kycb.core.services.expected.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.expected.v1.ExpectedActivityMapper;
import com.catalis.core.kycb.interfaces.dtos.expected.v1.ExpectedActivityDTO;
import com.catalis.core.kycb.models.entities.expected.v1.ExpectedActivity;
import com.catalis.core.kycb.models.repositories.expected.v1.ExpectedActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the expected activity service.
 */
@Service
@Transactional
public class ExpectedActivityServiceImpl implements ExpectedActivityService {

    @Autowired
    private ExpectedActivityRepository repository;

    @Autowired
    private ExpectedActivityMapper mapper;

    @Override
    public Mono<PaginationResponse<ExpectedActivityDTO>> findAll(FilterRequest<ExpectedActivityDTO> filterRequest) {
        return FilterUtils.createFilter(
                ExpectedActivity.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<ExpectedActivityDTO> create(ExpectedActivityDTO dto) {
        ExpectedActivity entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ExpectedActivityDTO> getById(Long activityId) {
        return repository.findById(activityId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<ExpectedActivityDTO> update(Long activityId, ExpectedActivityDTO dto) {
        return repository.findById(activityId)
                .flatMap(existingEntity -> {
                    ExpectedActivity updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setExpectedActivityId(activityId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long activityId) {
        return repository.deleteById(activityId);
    }
}