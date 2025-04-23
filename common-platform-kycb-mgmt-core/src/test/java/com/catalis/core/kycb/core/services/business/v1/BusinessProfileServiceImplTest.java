package com.catalis.core.kycb.core.services.business.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.business.v1.BusinessProfileMapper;
import com.catalis.core.kycb.interfaces.dtos.business.v1.BusinessProfileDTO;
import com.catalis.core.kycb.models.entities.business.v1.BusinessProfile;
import com.catalis.core.kycb.models.repositories.business.v1.BusinessProfileRepository;
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
public class BusinessProfileServiceImplTest {

    @Mock
    private BusinessProfileRepository repository;

    @Mock
    private BusinessProfileMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private BusinessProfileServiceImpl businessProfileService;

    private BusinessProfileDTO businessProfileDTO;
    private BusinessProfile businessProfile;
    private final Long BUSINESS_PROFILE_ID = 1L;
    private final Long PARTY_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        businessProfileDTO = new BusinessProfileDTO();
        businessProfileDTO.setBusinessProfileId(BUSINESS_PROFILE_ID);
        businessProfileDTO.setPartyId(PARTY_ID);
        
        businessProfile = new BusinessProfile();
        businessProfile.setBusinessProfileId(BUSINESS_PROFILE_ID);
        businessProfile.setPartyId(PARTY_ID);
        businessProfile.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(BusinessProfileDTO.class))).thenReturn(businessProfile);
        when(repository.save(any(BusinessProfile.class))).thenReturn(Mono.just(businessProfile));
        when(mapper.toDTO(any(BusinessProfile.class))).thenReturn(businessProfileDTO);

        // Act & Assert
        StepVerifier.create(businessProfileService.create(businessProfileDTO))
                .expectNext(businessProfileDTO)
                .verifyComplete();

        verify(mapper).toEntity(businessProfileDTO);
        verify(repository).save(businessProfile);
        verify(mapper).toDTO(businessProfile);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(BUSINESS_PROFILE_ID)).thenReturn(Mono.just(businessProfile));
        when(mapper.toDTO(businessProfile)).thenReturn(businessProfileDTO);

        // Act & Assert
        StepVerifier.create(businessProfileService.getById(BUSINESS_PROFILE_ID))
                .expectNext(businessProfileDTO)
                .verifyComplete();

        verify(repository).findById(BUSINESS_PROFILE_ID);
        verify(mapper).toDTO(businessProfile);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        BusinessProfile existingProfile = new BusinessProfile();
        existingProfile.setBusinessProfileId(BUSINESS_PROFILE_ID);
        existingProfile.setPartyId(PARTY_ID);
        existingProfile.setDateCreated(creationDate);
        
        BusinessProfile updatedProfile = new BusinessProfile();
        updatedProfile.setBusinessProfileId(BUSINESS_PROFILE_ID);
        updatedProfile.setPartyId(PARTY_ID);
        
        when(repository.findById(BUSINESS_PROFILE_ID)).thenReturn(Mono.just(existingProfile));
        when(mapper.toEntity(businessProfileDTO)).thenReturn(updatedProfile);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(BusinessProfile.class))).thenAnswer(invocation -> {
            BusinessProfile profile = invocation.getArgument(0);
            if (profile.getDateCreated() == null) {
                profile.setDateCreated(creationDate);
            }
            return Mono.just(profile);
        });
        
        when(mapper.toDTO(any(BusinessProfile.class))).thenReturn(businessProfileDTO);

        // Act & Assert
        StepVerifier.create(businessProfileService.update(BUSINESS_PROFILE_ID, businessProfileDTO))
                .expectNext(businessProfileDTO)
                .verifyComplete();

        verify(repository).findById(BUSINESS_PROFILE_ID);
        verify(mapper).toEntity(businessProfileDTO);
        verify(repository).save(any(BusinessProfile.class));
        verify(mapper).toDTO(any(BusinessProfile.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(BUSINESS_PROFILE_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(businessProfileService.delete(BUSINESS_PROFILE_ID))
                .verifyComplete();

        verify(repository).deleteById(BUSINESS_PROFILE_ID);
    }
}