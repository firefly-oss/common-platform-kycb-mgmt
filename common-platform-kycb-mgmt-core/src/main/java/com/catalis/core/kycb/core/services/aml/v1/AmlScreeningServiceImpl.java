package com.catalis.core.kycb.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.aml.v1.AmlScreeningMapper;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import com.catalis.core.kycb.interfaces.enums.screening.v1.ScreeningResultEnum;
import com.catalis.core.kycb.interfaces.enums.screening.v1.ScreeningTypeEnum;
import com.catalis.core.kycb.models.entities.aml.v1.AmlScreening;
import com.catalis.core.kycb.models.repositories.aml.v1.AmlScreeningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
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
    public Flux<AmlScreeningDTO> findByPartyId(Long partyId) {
        return repository.findByPartyId(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> create(AmlScreeningDTO dto) {
        AmlScreening entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> getById(Long amlScreeningId) {
        return repository.findById(amlScreeningId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> update(Long amlScreeningId, AmlScreeningDTO dto) {
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
    public Mono<AmlScreeningDTO> executeScreening(Long partyId, String screeningType) {
        // In a real implementation, this would call an external AML screening service
        // For now, we'll create a simulated screening result

        AmlScreening screening = new AmlScreening();
        screening.setPartyId(partyId);
        screening.setScreeningDate(LocalDateTime.now());
        screening.setScreeningType(ScreeningTypeEnum.valueOf(screeningType));
        screening.setScreeningProvider("Mock AML Provider");
        screening.setReferenceId(UUID.randomUUID().toString());

        // Simulate random results
        boolean matchesFound = Math.random() > 0.7; // 30% chance of finding matches
        screening.setMatchesFound(matchesFound);

        if (matchesFound) {
            screening.setMatchCount((int) (Math.random() * 5) + 1); // 1-5 matches
            screening.setScreeningResult(ScreeningResultEnum.REVIEW_REQUIRED);
        } else {
            screening.setMatchCount(0);
            screening.setScreeningResult(ScreeningResultEnum.CLEAR);
        }

        // Set next screening date to 1 year from now
        screening.setNextScreeningDate(LocalDateTime.now().plusYears(1));

        return repository.save(screening)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<AmlScreeningDTO> getLatestByPartyId(Long partyId) {
        return repository.findFirstByPartyIdOrderByScreeningDateDesc(partyId)
                .map(mapper::toDTO);
    }
}
