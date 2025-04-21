package com.catalis.core.kycb.core.services.location.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.location.v1.BusinessLocationMapper;
import com.catalis.core.kycb.interfaces.dtos.location.v1.BusinessLocationDTO;
import com.catalis.core.kycb.models.entities.location.v1.BusinessLocation;
import com.catalis.core.kycb.models.repositories.location.v1.BusinessLocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the business location service.
 */
@Service
@Transactional
public class BusinessLocationServiceImpl implements BusinessLocationService {

    @Autowired
    private BusinessLocationRepository repository;

    @Autowired
    private BusinessLocationMapper mapper;

    @Override
    public Mono<PaginationResponse<BusinessLocationDTO>> findAll(FilterRequest<BusinessLocationDTO> filterRequest) {
        return FilterUtils.createFilter(
                BusinessLocation.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Flux<BusinessLocationDTO> findByPartyId(Long partyId) {
        return repository.findByPartyId(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BusinessLocationDTO> create(BusinessLocationDTO dto) {
        BusinessLocation entity = mapper.toEntity(dto);

        // If this is set as primary, we need to ensure no other location for this party is primary
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
    public Mono<BusinessLocationDTO> getById(Long locationId) {
        return repository.findById(locationId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BusinessLocationDTO> update(Long locationId, BusinessLocationDTO dto) {
        return repository.findById(locationId)
                .flatMap(existingEntity -> {
                    BusinessLocation updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setBusinessLocationId(locationId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());

                    // If this is being set as primary, we need to ensure no other location for this party is primary
                    if (Boolean.TRUE.equals(updatedEntity.getIsPrimary()) && !Boolean.TRUE.equals(existingEntity.getIsPrimary())) {
                        return repository.findByPartyIdAndIsPrimaryTrue(updatedEntity.getPartyId())
                                .flatMap(existingPrimary -> {
                                    existingPrimary.setIsPrimary(false);
                                    return repository.save(existingPrimary);
                                })
                                .then(repository.save(updatedEntity).map(mapper::toDTO))
                                .switchIfEmpty(repository.save(updatedEntity).map(mapper::toDTO));
                    }

                    // Preserve verification data if not changing
                    if (existingEntity.getIsVerified() && updatedEntity.getIsVerified() == null) {
                        updatedEntity.setIsVerified(existingEntity.getIsVerified());
                        updatedEntity.setVerificationDate(existingEntity.getVerificationDate());
                        updatedEntity.setVerificationMethod(existingEntity.getVerificationMethod());
                    }

                    return repository.save(updatedEntity)
                            .map(mapper::toDTO);
                });
    }

    @Override
    public Mono<Void> delete(Long locationId) {
        return repository.deleteById(locationId);
    }

    @Override
    public Flux<BusinessLocationDTO> findByLocationTypeCode(String locationTypeCode) {
        return repository.findByLocationTypeCode(locationTypeCode)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findPrimaryLocations() {
        return repository.findByIsPrimary(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findByCountry(String countryIsoCode) {
        return repository.findByCountryIsoCode(countryIsoCode)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findByCity(String city) {
        return repository.findByCity(city)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findByEmployeeCountBetween(Integer minEmployees, Integer maxEmployees) {
        return repository.findByEmployeeCountBetween(minEmployees, maxEmployees)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findVerifiedLocations() {
        return repository.findByIsVerified(true)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findByVerificationMethod(String verificationMethod) {
        return repository.findByVerificationMethod(verificationMethod)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessLocationDTO> findByVerificationDateBetween(LocalDateTime startDate, LocalDateTime endDate) {
        return repository.findByVerificationDateBetween(startDate, endDate)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BusinessLocationDTO> getPrimaryLocationByPartyId(Long partyId) {
        return repository.findByPartyIdAndIsPrimaryTrue(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<BusinessLocationDTO> setPrimaryLocation(Long locationId) {
        return repository.findById(locationId)
                .flatMap(entity -> {
                    // First, find and update any existing primary location for this party
                    return repository.findByPartyIdAndIsPrimaryTrue(entity.getPartyId())
                            .flatMap(existingPrimary -> {
                                existingPrimary.setIsPrimary(false);
                                return repository.save(existingPrimary);
                            })
                            .then(Mono.just(entity))
                            .switchIfEmpty(Mono.just(entity))
                            .flatMap(updatedEntity -> {
                                // Then set this location as primary
                                updatedEntity.setIsPrimary(true);
                                return repository.save(updatedEntity);
                            })
                            .map(mapper::toDTO);
                });
    }

    @Override
    public Mono<BusinessLocationDTO> verifyLocation(Long locationId, String verificationMethod) {
        return repository.findById(locationId)
                .flatMap(entity -> {
                    entity.setIsVerified(true);
                    entity.setVerificationDate(LocalDateTime.now());
                    entity.setVerificationMethod(verificationMethod);
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }
}
