package com.catalis.core.kycb.core.services.economic.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.economic.v1.EconomicActivityMapper;
import com.catalis.core.kycb.interfaces.dtos.economic.v1.EconomicActivityDTO;
import com.catalis.core.kycb.models.entities.economic.v1.EconomicActivity;
import com.catalis.core.kycb.models.repositories.economic.v1.EconomicActivityRepository;
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
public class EconomicActivityServiceImplTest {

    @Mock
    private EconomicActivityRepository repository;

    @Mock
    private EconomicActivityMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private EconomicActivityServiceImpl economicActivityService;

    private EconomicActivityDTO economicActivityDTO;
    private EconomicActivity economicActivity;
    private final Long ECONOMIC_ACTIVITY_ID = 1L;
    private final Long PARTY_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        economicActivityDTO = new EconomicActivityDTO();
        economicActivityDTO.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        economicActivityDTO.setPartyId(PARTY_ID);
        economicActivityDTO.setIsPrimary(false);
        
        economicActivity = new EconomicActivity();
        economicActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        economicActivity.setPartyId(PARTY_ID);
        economicActivity.setIsPrimary(false);
        economicActivity.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(EconomicActivityDTO.class))).thenReturn(economicActivity);
        when(repository.save(any(EconomicActivity.class))).thenReturn(Mono.just(economicActivity));
        when(mapper.toDTO(any(EconomicActivity.class))).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.create(economicActivityDTO))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(mapper).toEntity(economicActivityDTO);
        verify(repository).save(economicActivity);
        verify(mapper).toDTO(economicActivity);
    }

    @Test
    void testCreatePrimaryActivity() {
        // Arrange
        economicActivityDTO.setIsPrimary(true);
        economicActivity.setIsPrimary(true);

        EconomicActivity existingPrimaryActivity = new EconomicActivity();
        existingPrimaryActivity.setEconomicActivityId(2L);
        existingPrimaryActivity.setPartyId(PARTY_ID);
        existingPrimaryActivity.setIsPrimary(true);

        when(mapper.toEntity(any(EconomicActivityDTO.class))).thenReturn(economicActivity);
        when(repository.findByPartyIdAndIsPrimaryTrue(PARTY_ID)).thenReturn(Mono.just(existingPrimaryActivity));
        when(repository.save(existingPrimaryActivity)).thenReturn(Mono.just(existingPrimaryActivity));
        when(repository.save(economicActivity)).thenReturn(Mono.just(economicActivity));
        when(mapper.toDTO(economicActivity)).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.create(economicActivityDTO))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(repository).findByPartyIdAndIsPrimaryTrue(PARTY_ID);
        verify(repository).save(existingPrimaryActivity);
        // The save method is called twice due to the implementation using switchIfEmpty
        verify(repository, times(2)).save(economicActivity);
        verify(mapper).toDTO(economicActivity);

        // Verify that the existing primary activity is no longer primary
        verify(repository).save(argThat(activity -> 
            activity.getEconomicActivityId().equals(2L) && !activity.getIsPrimary()));
    }

    @Test
    void testCreatePrimaryActivityWithNoExistingPrimary() {
        // Arrange
        economicActivityDTO.setIsPrimary(true);
        economicActivity.setIsPrimary(true);

        when(mapper.toEntity(any(EconomicActivityDTO.class))).thenReturn(economicActivity);
        when(repository.findByPartyIdAndIsPrimaryTrue(PARTY_ID)).thenReturn(Mono.empty());
        when(repository.save(economicActivity)).thenReturn(Mono.just(economicActivity));
        when(mapper.toDTO(economicActivity)).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.create(economicActivityDTO))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(repository).findByPartyIdAndIsPrimaryTrue(PARTY_ID);
        // The save method is called twice due to the implementation using switchIfEmpty
        verify(repository, times(2)).save(economicActivity);
        verify(mapper).toDTO(economicActivity);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(ECONOMIC_ACTIVITY_ID)).thenReturn(Mono.just(economicActivity));
        when(mapper.toDTO(economicActivity)).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.getById(ECONOMIC_ACTIVITY_ID))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(repository).findById(ECONOMIC_ACTIVITY_ID);
        verify(mapper).toDTO(economicActivity);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        EconomicActivity existingActivity = new EconomicActivity();
        existingActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        existingActivity.setPartyId(PARTY_ID);
        existingActivity.setIsPrimary(false);
        existingActivity.setDateCreated(creationDate);
        
        EconomicActivity updatedActivity = new EconomicActivity();
        updatedActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        updatedActivity.setPartyId(PARTY_ID);
        updatedActivity.setIsPrimary(false);
        
        when(repository.findById(ECONOMIC_ACTIVITY_ID)).thenReturn(Mono.just(existingActivity));
        when(mapper.toEntity(economicActivityDTO)).thenReturn(updatedActivity);
        when(repository.save(updatedActivity)).thenReturn(Mono.just(updatedActivity));
        when(mapper.toDTO(updatedActivity)).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.update(ECONOMIC_ACTIVITY_ID, economicActivityDTO))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(repository).findById(ECONOMIC_ACTIVITY_ID);
        verify(mapper).toEntity(economicActivityDTO);
        verify(repository).save(updatedActivity);
        verify(mapper).toDTO(updatedActivity);
    }

    @Test
    void testUpdateToPrimary() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        EconomicActivity existingActivity = new EconomicActivity();
        existingActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        existingActivity.setPartyId(PARTY_ID);
        existingActivity.setIsPrimary(false);
        existingActivity.setDateCreated(creationDate);
        
        EconomicActivity updatedActivity = new EconomicActivity();
        updatedActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        updatedActivity.setPartyId(PARTY_ID);
        updatedActivity.setIsPrimary(true);
        
        EconomicActivity existingPrimaryActivity = new EconomicActivity();
        existingPrimaryActivity.setEconomicActivityId(2L);
        existingPrimaryActivity.setPartyId(PARTY_ID);
        existingPrimaryActivity.setIsPrimary(true);
        
        economicActivityDTO.setIsPrimary(true);
        
        when(repository.findById(ECONOMIC_ACTIVITY_ID)).thenReturn(Mono.just(existingActivity));
        when(mapper.toEntity(economicActivityDTO)).thenReturn(updatedActivity);
        when(repository.findByPartyIdAndIsPrimaryTrue(PARTY_ID)).thenReturn(Mono.just(existingPrimaryActivity));
        when(repository.save(existingPrimaryActivity)).thenReturn(Mono.just(existingPrimaryActivity));
        when(repository.save(updatedActivity)).thenReturn(Mono.just(updatedActivity));
        when(mapper.toDTO(updatedActivity)).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.update(ECONOMIC_ACTIVITY_ID, economicActivityDTO))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(repository).findById(ECONOMIC_ACTIVITY_ID);
        verify(repository).findByPartyIdAndIsPrimaryTrue(PARTY_ID);
        verify(repository).save(existingPrimaryActivity);
        // The save method is called twice due to the implementation using switchIfEmpty
        verify(repository, times(2)).save(updatedActivity);
        verify(mapper).toDTO(updatedActivity);

        // Verify that the existing primary activity is no longer primary
        verify(repository).save(argThat(activity -> 
            activity.getEconomicActivityId().equals(2L) && !activity.getIsPrimary()));
    }

    @Test
    void testUpdateToPrimaryWithNoExistingPrimary() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        EconomicActivity existingActivity = new EconomicActivity();
        existingActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        existingActivity.setPartyId(PARTY_ID);
        existingActivity.setIsPrimary(false);
        existingActivity.setDateCreated(creationDate);
        
        EconomicActivity updatedActivity = new EconomicActivity();
        updatedActivity.setEconomicActivityId(ECONOMIC_ACTIVITY_ID);
        updatedActivity.setPartyId(PARTY_ID);
        updatedActivity.setIsPrimary(true);
        
        economicActivityDTO.setIsPrimary(true);
        
        when(repository.findById(ECONOMIC_ACTIVITY_ID)).thenReturn(Mono.just(existingActivity));
        when(mapper.toEntity(economicActivityDTO)).thenReturn(updatedActivity);
        when(repository.findByPartyIdAndIsPrimaryTrue(PARTY_ID)).thenReturn(Mono.empty());
        when(repository.save(updatedActivity)).thenReturn(Mono.just(updatedActivity));
        when(mapper.toDTO(updatedActivity)).thenReturn(economicActivityDTO);

        // Act & Assert
        StepVerifier.create(economicActivityService.update(ECONOMIC_ACTIVITY_ID, economicActivityDTO))
                .expectNext(economicActivityDTO)
                .verifyComplete();

        verify(repository).findById(ECONOMIC_ACTIVITY_ID);
        verify(repository).findByPartyIdAndIsPrimaryTrue(PARTY_ID);
        // The save method is called twice due to the implementation using switchIfEmpty
        verify(repository, times(2)).save(updatedActivity);
        verify(mapper).toDTO(updatedActivity);
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(ECONOMIC_ACTIVITY_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(economicActivityService.delete(ECONOMIC_ACTIVITY_ID))
                .verifyComplete();

        verify(repository).deleteById(ECONOMIC_ACTIVITY_ID);
    }
}