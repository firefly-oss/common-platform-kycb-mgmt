package com.catalis.core.kycb.core.services.source.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.source.v1.SourceOfFundsMapper;
import com.catalis.core.kycb.interfaces.dtos.source.v1.SourceOfFundsDTO;
import com.catalis.core.kycb.models.entities.source.v1.SourceOfFunds;
import com.catalis.core.kycb.models.repositories.source.v1.SourceOfFundsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the source of funds service.
 */
@Service
@Transactional
public class SourceOfFundsServiceImpl implements SourceOfFundsService {

    @Autowired
    private SourceOfFundsRepository repository;

    @Autowired
    private SourceOfFundsMapper mapper;

    @Override
    public Mono<PaginationResponse<SourceOfFundsDTO>> findAll(FilterRequest<SourceOfFundsDTO> filterRequest) {
        return FilterUtils.createFilter(
                SourceOfFunds.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<SourceOfFundsDTO> create(SourceOfFundsDTO dto) {
        SourceOfFunds entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SourceOfFundsDTO> getById(Long sourceId) {
        return repository.findById(sourceId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SourceOfFundsDTO> update(Long sourceId, SourceOfFundsDTO dto) {
        return repository.findById(sourceId)
                .flatMap(existingEntity -> {
                    SourceOfFunds updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setSourceOfFundsId(sourceId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    // Preserve verification data if not changing
                    if (existingEntity.getIsVerified() && updatedEntity.getIsVerified() == null) {
                        updatedEntity.setIsVerified(existingEntity.getIsVerified());
                        updatedEntity.setVerificationDate(existingEntity.getVerificationDate());
                        updatedEntity.setVerificationMethod(existingEntity.getVerificationMethod());
                    }
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long sourceId) {
        return repository.deleteById(sourceId);
    }
}
