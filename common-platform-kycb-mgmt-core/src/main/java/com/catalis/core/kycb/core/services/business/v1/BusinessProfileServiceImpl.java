package com.catalis.core.kycb.core.services.business.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.business.v1.BusinessProfileMapper;
import com.catalis.core.kycb.interfaces.dtos.business.v1.BusinessProfileDTO;
import com.catalis.core.kycb.models.entities.business.v1.BusinessProfile;
import com.catalis.core.kycb.models.repositories.business.v1.BusinessProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

/**
 * Implementation of the business profile service.
 */
@Service
@Transactional
public class BusinessProfileServiceImpl implements BusinessProfileService {

    @Autowired
    private BusinessProfileRepository repository;

    @Autowired
    private BusinessProfileMapper mapper;

    @Override
    public Mono<PaginationResponse<BusinessProfileDTO>> findAll(FilterRequest<BusinessProfileDTO> filterRequest) {
        return FilterUtils.createFilter(
                BusinessProfile.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<BusinessProfileDTO> create(BusinessProfileDTO dto) {
        BusinessProfile entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BusinessProfileDTO> getById(Long businessProfileId) {
        return repository.findById(businessProfileId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BusinessProfileDTO> update(Long businessProfileId, BusinessProfileDTO dto) {
        return repository.findById(businessProfileId)
                .flatMap(existingEntity -> {
                    BusinessProfile updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setBusinessProfileId(businessProfileId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long businessProfileId) {
        return repository.deleteById(businessProfileId);
    }
}
