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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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
    public Flux<BusinessProfileDTO> findByPartyId(Long partyId) {
        return repository.findByPartyId(partyId)
                .map(mapper::toDTO);
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

    @Override
    public Mono<BusinessProfileDTO> getLatestByPartyId(Long partyId) {
        return repository.findFirstByPartyIdOrderByDateCreatedDesc(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByLegalFormCode(String legalFormCode) {
        return repository.findByLegalFormCode(legalFormCode)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByIncorporationYear(Integer incorporationYear) {
        return repository.findByIncorporationYear(incorporationYear)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByEmployeeCountBetween(Integer minEmployees, Integer maxEmployees) {
        return repository.findByEmployeeCountBetween(minEmployees, maxEmployees)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByEmployeeRangeCode(String employeeRangeCode) {
        return repository.findByEmployeeRangeCode(employeeRangeCode)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByAnnualRevenueBetween(BigDecimal minRevenue, BigDecimal maxRevenue) {
        return repository.findByAnnualRevenueBetween(minRevenue, maxRevenue)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByRevenueRangeCode(String revenueRangeCode) {
        return repository.findByRevenueRangeCode(revenueRangeCode)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByIsRegulated(Boolean isRegulated) {
        return repository.findByIsRegulated(isRegulated)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByRegulatoryAuthority(String regulatoryAuthority) {
        return repository.findByRegulatoryAuthority(regulatoryAuthority)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByCompanyStatusCode(String companyStatusCode) {
        return repository.findByCompanyStatusCode(companyStatusCode)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<BusinessProfileDTO> findByIsPublicEntity(Boolean isPublicEntity) {
        return repository.findByIsPublicEntity(isPublicEntity)
                .map(mapper::toDTO);
    }
}
