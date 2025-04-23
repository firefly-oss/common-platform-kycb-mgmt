package com.catalis.core.kycb.core.services.aml.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.aml.v1.AmlScreeningMapper;
import com.catalis.core.kycb.interfaces.dtos.aml.v1.AmlScreeningDTO;
import com.catalis.core.kycb.models.entities.aml.v1.AmlScreening;
import com.catalis.core.kycb.models.repositories.aml.v1.AmlScreeningRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AmlScreeningServiceImplTest {

    @Mock
    private AmlScreeningRepository repository;

    @Mock
    private AmlScreeningMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private AmlScreeningServiceImpl amlScreeningService;

    private AmlScreeningDTO amlScreeningDTO;
    private AmlScreening amlScreening;
    private final Long AML_SCREENING_ID = 1L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        amlScreeningDTO = new AmlScreeningDTO();
        amlScreeningDTO.setAmlScreeningId(AML_SCREENING_ID);
        
        amlScreening = new AmlScreening();
        amlScreening.setAmlScreeningId(AML_SCREENING_ID);
        amlScreening.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(AmlScreeningDTO.class))).thenReturn(amlScreening);
        when(repository.save(any(AmlScreening.class))).thenReturn(Mono.just(amlScreening));
        when(mapper.toDTO(any(AmlScreening.class))).thenReturn(amlScreeningDTO);

        // Act & Assert
        StepVerifier.create(amlScreeningService.create(amlScreeningDTO))
                .expectNext(amlScreeningDTO)
                .verifyComplete();

        verify(mapper).toEntity(amlScreeningDTO);
        verify(repository).save(amlScreening);
        verify(mapper).toDTO(amlScreening);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(AML_SCREENING_ID)).thenReturn(Mono.just(amlScreening));
        when(mapper.toDTO(amlScreening)).thenReturn(amlScreeningDTO);

        // Act & Assert
        StepVerifier.create(amlScreeningService.getById(AML_SCREENING_ID))
                .expectNext(amlScreeningDTO)
                .verifyComplete();

        verify(repository).findById(AML_SCREENING_ID);
        verify(mapper).toDTO(amlScreening);
    }

    @Test
    void testUpdate() {
        // Arrange
        when(repository.findById(AML_SCREENING_ID)).thenReturn(Mono.just(amlScreening));
        when(mapper.toEntity(amlScreeningDTO)).thenReturn(amlScreening);
        when(repository.save(amlScreening)).thenReturn(Mono.just(amlScreening));
        when(mapper.toDTO(amlScreening)).thenReturn(amlScreeningDTO);

        // Act & Assert
        StepVerifier.create(amlScreeningService.update(AML_SCREENING_ID, amlScreeningDTO))
                .expectNext(amlScreeningDTO)
                .verifyComplete();

        verify(repository).findById(AML_SCREENING_ID);
        verify(mapper).toEntity(amlScreeningDTO);
        verify(repository).save(amlScreening);
        verify(mapper).toDTO(amlScreening);
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(AML_SCREENING_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(amlScreeningService.delete(AML_SCREENING_ID))
                .verifyComplete();

        verify(repository).deleteById(AML_SCREENING_ID);
    }
}