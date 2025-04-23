package com.catalis.core.kycb.core.services.economic.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.economic.v1.EconomicActivityMapper;
import com.catalis.core.kycb.interfaces.dtos.economic.v1.EconomicActivityDTO;
import com.catalis.core.kycb.models.entities.economic.v1.EconomicActivity;
import com.catalis.core.kycb.models.repositories.economic.v1.EconomicActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the economic activity service.
 */
@Service
@Transactional
public class EconomicActivityServiceImpl implements EconomicActivityService {

    @Autowired
    private EconomicActivityRepository repository;

    @Autowired
    private EconomicActivityMapper mapper;

    @Override
    public Mono<PaginationResponse<EconomicActivityDTO>> findAll(FilterRequest<EconomicActivityDTO> filterRequest) {
        return FilterUtils.createFilter(
                EconomicActivity.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<EconomicActivityDTO> create(EconomicActivityDTO dto) {
        EconomicActivity entity = mapper.toEntity(dto);

        // If this is set as primary, we need to ensure no other activity for this party is primary
        if (Boolean.TRUE.equals(entity.getIsPrimary())) {
            return repository.findByPartyIdAndIsPrimaryTrue(entity.getPartyId())
                    .flatMap(existingPrimary -> {
                        existingPrimary.setIsPrimary(false);
                        return repository.save(existingPrimary);
                    })
                    .then(repository.save(entity).map(mapper::toDTO))
                    .switchIfEmpty(repository.save(entity).map(mapper::toDTO));
        }

        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EconomicActivityDTO> getById(Long activityId) {
        return repository.findById(activityId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<EconomicActivityDTO> update(Long activityId, EconomicActivityDTO dto) {
        return repository.findById(activityId)
                .flatMap(existingEntity -> {
                    EconomicActivity updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setEconomicActivityId(activityId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());

                    // If this is being set as primary, we need to ensure no other activity for this party is primary
                    if (Boolean.TRUE.equals(updatedEntity.getIsPrimary()) && !Boolean.TRUE.equals(existingEntity.getIsPrimary())) {
                        return repository.findByPartyIdAndIsPrimaryTrue(updatedEntity.getPartyId())
                                .flatMap(existingPrimary -> {
                                    existingPrimary.setIsPrimary(false);
                                    return repository.save(existingPrimary);
                                })
                                .then(repository.save(updatedEntity).map(mapper::toDTO))
                                .switchIfEmpty(repository.save(updatedEntity).map(mapper::toDTO));
                    }

                    return repository.save(updatedEntity)
                            .map(mapper::toDTO);
                });
    }

    @Override
    public Mono<Void> delete(Long activityId) {
        return repository.deleteById(activityId);
    }
}
