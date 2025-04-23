package com.catalis.core.kycb.core.services.power.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.power.v1.PowerOfAttorneyMapper;
import com.catalis.core.kycb.interfaces.dtos.power.v1.PowerOfAttorneyDTO;
import com.catalis.core.kycb.interfaces.enums.power.v1.PowerTypeEnum;
import com.catalis.core.kycb.models.entities.power.v1.PowerOfAttorney;
import com.catalis.core.kycb.models.repositories.power.v1.PowerOfAttorneyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PowerOfAttorneyServiceImplTest {

    @Mock
    private PowerOfAttorneyRepository repository;

    @Mock
    private PowerOfAttorneyMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private PowerOfAttorneyServiceImpl powerOfAttorneyService;

    private PowerOfAttorneyDTO powerOfAttorneyDTO;
    private PowerOfAttorney powerOfAttorney;
    private final Long POWER_OF_ATTORNEY_ID = 1L;
    private final Long PARTY_ID = 100L;
    private final Long ATTORNEY_ID = 200L;
    private final Long CORPORATE_DOCUMENT_ID = 300L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        powerOfAttorneyDTO = new PowerOfAttorneyDTO();
        powerOfAttorneyDTO.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        powerOfAttorneyDTO.setPartyId(PARTY_ID);
        powerOfAttorneyDTO.setAttorneyId(ATTORNEY_ID);
        powerOfAttorneyDTO.setCorporateDocumentId(CORPORATE_DOCUMENT_ID);
        powerOfAttorneyDTO.setPowerType("LIMITED");
        powerOfAttorneyDTO.setPowerScope("Financial transactions up to $10,000");
        powerOfAttorneyDTO.setJointSignatureRequired(true);
        powerOfAttorneyDTO.setJointSignatureCount(2);
        powerOfAttorneyDTO.setJointSignatureNotes("Requires signature of CFO or CEO");
        powerOfAttorneyDTO.setFinancialLimit(new BigDecimal("10000.00"));
        powerOfAttorneyDTO.setCurrency("USD");
        powerOfAttorneyDTO.setEffectiveDate(LocalDateTime.now());
        powerOfAttorneyDTO.setExpiryDate(LocalDateTime.now().plusYears(1));
        powerOfAttorneyDTO.setIsVerified(false);
        powerOfAttorneyDTO.setIsBastanteoCompleted(false);
        
        powerOfAttorney = new PowerOfAttorney();
        powerOfAttorney.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        powerOfAttorney.setPartyId(PARTY_ID);
        powerOfAttorney.setAttorneyId(ATTORNEY_ID);
        powerOfAttorney.setCorporateDocumentId(CORPORATE_DOCUMENT_ID);
        powerOfAttorney.setPowerType(PowerTypeEnum.LIMITED);
        powerOfAttorney.setPowerScope("Financial transactions up to $10,000");
        powerOfAttorney.setJointSignatureRequired(true);
        powerOfAttorney.setJointSignatureCount(2);
        powerOfAttorney.setJointSignatureNotes("Requires signature of CFO or CEO");
        powerOfAttorney.setFinancialLimit(new BigDecimal("10000.00"));
        powerOfAttorney.setCurrency("USD");
        powerOfAttorney.setEffectiveDate(LocalDateTime.now());
        powerOfAttorney.setExpiryDate(LocalDateTime.now().plusYears(1));
        powerOfAttorney.setIsVerified(false);
        powerOfAttorney.setIsBastanteoCompleted(false);
        powerOfAttorney.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(PowerOfAttorneyDTO.class))).thenReturn(powerOfAttorney);
        when(repository.save(any(PowerOfAttorney.class))).thenReturn(Mono.just(powerOfAttorney));
        when(mapper.toDTO(any(PowerOfAttorney.class))).thenReturn(powerOfAttorneyDTO);

        // Act & Assert
        StepVerifier.create(powerOfAttorneyService.create(powerOfAttorneyDTO))
                .expectNext(powerOfAttorneyDTO)
                .verifyComplete();

        verify(mapper).toEntity(powerOfAttorneyDTO);
        verify(repository).save(powerOfAttorney);
        verify(mapper).toDTO(powerOfAttorney);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(POWER_OF_ATTORNEY_ID)).thenReturn(Mono.just(powerOfAttorney));
        when(mapper.toDTO(powerOfAttorney)).thenReturn(powerOfAttorneyDTO);

        // Act & Assert
        StepVerifier.create(powerOfAttorneyService.getById(POWER_OF_ATTORNEY_ID))
                .expectNext(powerOfAttorneyDTO)
                .verifyComplete();

        verify(repository).findById(POWER_OF_ATTORNEY_ID);
        verify(mapper).toDTO(powerOfAttorney);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        PowerOfAttorney existingPoa = new PowerOfAttorney();
        existingPoa.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        existingPoa.setPartyId(PARTY_ID);
        existingPoa.setAttorneyId(ATTORNEY_ID);
        existingPoa.setPowerType(PowerTypeEnum.LIMITED);
        existingPoa.setDateCreated(creationDate);
        existingPoa.setIsVerified(false);
        
        PowerOfAttorney updatedPoa = new PowerOfAttorney();
        updatedPoa.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        updatedPoa.setPartyId(PARTY_ID);
        updatedPoa.setAttorneyId(ATTORNEY_ID);
        updatedPoa.setPowerType(PowerTypeEnum.LIMITED);
        updatedPoa.setPowerScope("Updated scope");
        updatedPoa.setFinancialLimit(new BigDecimal("20000.00"));
        
        PowerOfAttorneyDTO updatedDTO = new PowerOfAttorneyDTO();
        updatedDTO.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        updatedDTO.setPartyId(PARTY_ID);
        updatedDTO.setAttorneyId(ATTORNEY_ID);
        updatedDTO.setPowerType("LIMITED");
        updatedDTO.setPowerScope("Updated scope");
        updatedDTO.setFinancialLimit(new BigDecimal("20000.00"));
        
        when(repository.findById(POWER_OF_ATTORNEY_ID)).thenReturn(Mono.just(existingPoa));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedPoa);
        when(repository.save(any(PowerOfAttorney.class))).thenReturn(Mono.just(updatedPoa));
        when(mapper.toDTO(any(PowerOfAttorney.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(powerOfAttorneyService.update(POWER_OF_ATTORNEY_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(POWER_OF_ATTORNEY_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(PowerOfAttorney.class));
        verify(mapper).toDTO(any(PowerOfAttorney.class));
        
        // Verify that the creation date is preserved
        verify(repository).save(argThat(poa -> 
            poa.getDateCreated() != null && poa.getDateCreated().equals(creationDate)));
    }

    @Test
    void testUpdatePreservesVerificationData() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime verificationDate = LocalDateTime.now();
        
        PowerOfAttorney existingPoa = new PowerOfAttorney();
        existingPoa.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        existingPoa.setPartyId(PARTY_ID);
        existingPoa.setAttorneyId(ATTORNEY_ID);
        existingPoa.setPowerType(PowerTypeEnum.LIMITED);
        existingPoa.setDateCreated(creationDate);
        existingPoa.setIsVerified(true);
        existingPoa.setVerificationDate(verificationDate);
        existingPoa.setVerificationMethod("LEGAL_REVIEW");
        existingPoa.setVerifyingLegalCounsel("John Doe, Esq.");
        
        PowerOfAttorney updatedPoa = new PowerOfAttorney();
        updatedPoa.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        updatedPoa.setPartyId(PARTY_ID);
        updatedPoa.setAttorneyId(ATTORNEY_ID);
        updatedPoa.setPowerType(PowerTypeEnum.LIMITED);
        updatedPoa.setPowerScope("Updated scope");
        // No verification data set in the updated entity
        
        PowerOfAttorneyDTO updatedDTO = new PowerOfAttorneyDTO();
        updatedDTO.setPowerOfAttorneyId(POWER_OF_ATTORNEY_ID);
        updatedDTO.setPartyId(PARTY_ID);
        updatedDTO.setAttorneyId(ATTORNEY_ID);
        updatedDTO.setPowerType("LIMITED");
        updatedDTO.setPowerScope("Updated scope");
        // No verification data set in the updated DTO
        
        when(repository.findById(POWER_OF_ATTORNEY_ID)).thenReturn(Mono.just(existingPoa));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedPoa);
        
        // Mock the save method to verify verification data is preserved
        when(repository.save(any(PowerOfAttorney.class))).thenAnswer(invocation -> {
            PowerOfAttorney savedPoa = invocation.getArgument(0);
            // Verify that verification data is preserved
            if (savedPoa.getIsVerified() == null && existingPoa.getIsVerified()) {
                savedPoa.setIsVerified(existingPoa.getIsVerified());
                savedPoa.setVerificationDate(existingPoa.getVerificationDate());
                savedPoa.setVerificationMethod(existingPoa.getVerificationMethod());
                savedPoa.setVerifyingLegalCounsel(existingPoa.getVerifyingLegalCounsel());
            }
            return Mono.just(savedPoa);
        });
        
        when(mapper.toDTO(any(PowerOfAttorney.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(powerOfAttorneyService.update(POWER_OF_ATTORNEY_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(POWER_OF_ATTORNEY_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(PowerOfAttorney.class));
        verify(mapper).toDTO(any(PowerOfAttorney.class));
        
        // Verify that verification data is preserved
        verify(repository).save(argThat(poa -> 
            poa.getIsVerified() != null && poa.getIsVerified() &&
            poa.getVerificationDate() != null &&
            poa.getVerificationMethod() != null &&
            poa.getVerifyingLegalCounsel() != null));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(POWER_OF_ATTORNEY_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(powerOfAttorneyService.delete(POWER_OF_ATTORNEY_ID))
                .verifyComplete();

        verify(repository).deleteById(POWER_OF_ATTORNEY_ID);
    }
}