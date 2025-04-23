package com.catalis.core.kycb.core.services.corporate.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.corporate.v1.CorporateStructureMapper;
import com.catalis.core.kycb.interfaces.dtos.corporate.v1.CorporateStructureDTO;
import com.catalis.core.kycb.models.entities.corporate.v1.CorporateStructure;
import com.catalis.core.kycb.models.repositories.corporate.v1.CorporateStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the corporate structure service.
 */
@Service
@Transactional
public class CorporateStructureServiceImpl implements CorporateStructureService {

    @Autowired
    private CorporateStructureRepository repository;

    @Autowired
    private CorporateStructureMapper mapper;

    @Override
    public Mono<PaginationResponse<CorporateStructureDTO>> findAll(FilterRequest<CorporateStructureDTO> filterRequest) {
        return FilterUtils.createFilter(
                CorporateStructure.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<CorporateStructureDTO> create(CorporateStructureDTO dto) {
        CorporateStructure entity = mapper.toEntity(dto);
        // Set start date to now if not provided
        if (entity.getStartDate() == null) {
            entity.setStartDate(LocalDateTime.now());
        }
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateStructureDTO> getById(Long structureId) {
        return repository.findById(structureId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateStructureDTO> update(Long structureId, CorporateStructureDTO dto) {
        return repository.findById(structureId)
                .flatMap(existingEntity -> {
                    CorporateStructure updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCorporateStructureId(structureId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    // Preserve verification data if not changing
                    if (existingEntity.getIsVerified() && updatedEntity.getIsVerified() == null) {
                        updatedEntity.setIsVerified(existingEntity.getIsVerified());
                        updatedEntity.setVerificationDate(existingEntity.getVerificationDate());
                    }
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long structureId) {
        return repository.deleteById(structureId);
    }
}
