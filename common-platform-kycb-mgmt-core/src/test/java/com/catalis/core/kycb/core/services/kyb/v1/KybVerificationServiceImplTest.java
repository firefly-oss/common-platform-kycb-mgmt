package com.catalis.core.kycb.core.services.kyb.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.kyb.v1.KybVerificationMapper;
import com.catalis.core.kycb.interfaces.dtos.kyb.v1.KybVerificationDTO;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.interfaces.enums.verification.v1.VerificationStatusEnum;
import com.catalis.core.kycb.models.entities.kyb.v1.KybVerification;
import com.catalis.core.kycb.models.repositories.kyb.v1.KybVerificationRepository;
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
public class KybVerificationServiceImplTest {

    @Mock
    private KybVerificationRepository repository;

    @Mock
    private KybVerificationMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private KybVerificationServiceImpl kybVerificationService;

    private KybVerificationDTO kybVerificationDTO;
    private KybVerification kybVerification;
    private final Long KYB_VERIFICATION_ID = 1L;
    private final Long PARTY_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        kybVerificationDTO = new KybVerificationDTO();
        kybVerificationDTO.setKybVerificationId(KYB_VERIFICATION_ID);
        kybVerificationDTO.setPartyId(PARTY_ID);
        kybVerificationDTO.setVerificationStatus("PENDING");
        kybVerificationDTO.setVerificationDate(LocalDateTime.now());
        kybVerificationDTO.setMercantileRegistryVerified(true);
        kybVerificationDTO.setDeedOfIncorporationVerified(true);
        kybVerificationDTO.setBusinessStructureVerified(false);
        kybVerificationDTO.setUboVerified(false);
        kybVerificationDTO.setTaxIdVerified(true);
        kybVerificationDTO.setOperatingLicenseVerified(false);
        kybVerificationDTO.setVerificationNotes("Initial verification in progress");
        kybVerificationDTO.setRiskScore(65);
        kybVerificationDTO.setRiskLevel("MEDIUM");
        kybVerificationDTO.setNextReviewDate(LocalDateTime.now().plusMonths(6));
        
        kybVerification = new KybVerification();
        kybVerification.setKybVerificationId(KYB_VERIFICATION_ID);
        kybVerification.setPartyId(PARTY_ID);
        kybVerification.setVerificationStatus(VerificationStatusEnum.PENDING);
        kybVerification.setVerificationDate(LocalDateTime.now());
        kybVerification.setMercantileRegistryVerified(true);
        kybVerification.setDeedOfIncorporationVerified(true);
        kybVerification.setBusinessStructureVerified(false);
        kybVerification.setUboVerified(false);
        kybVerification.setTaxIdVerified(true);
        kybVerification.setOperatingLicenseVerified(false);
        kybVerification.setVerificationNotes("Initial verification in progress");
        kybVerification.setRiskScore(65);
        kybVerification.setRiskLevel(RiskLevelEnum.MEDIUM);
        kybVerification.setNextReviewDate(LocalDateTime.now().plusMonths(6));
        kybVerification.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(KybVerificationDTO.class))).thenReturn(kybVerification);
        when(repository.save(any(KybVerification.class))).thenReturn(Mono.just(kybVerification));
        when(mapper.toDTO(any(KybVerification.class))).thenReturn(kybVerificationDTO);

        // Act & Assert
        StepVerifier.create(kybVerificationService.create(kybVerificationDTO))
                .expectNext(kybVerificationDTO)
                .verifyComplete();

        verify(mapper).toEntity(kybVerificationDTO);
        verify(repository).save(kybVerification);
        verify(mapper).toDTO(kybVerification);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(KYB_VERIFICATION_ID)).thenReturn(Mono.just(kybVerification));
        when(mapper.toDTO(kybVerification)).thenReturn(kybVerificationDTO);

        // Act & Assert
        StepVerifier.create(kybVerificationService.getById(KYB_VERIFICATION_ID))
                .expectNext(kybVerificationDTO)
                .verifyComplete();

        verify(repository).findById(KYB_VERIFICATION_ID);
        verify(mapper).toDTO(kybVerification);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        KybVerification existingVerification = new KybVerification();
        existingVerification.setKybVerificationId(KYB_VERIFICATION_ID);
        existingVerification.setPartyId(PARTY_ID);
        existingVerification.setVerificationStatus(VerificationStatusEnum.PENDING);
        existingVerification.setDateCreated(creationDate);
        
        KybVerification updatedVerification = new KybVerification();
        updatedVerification.setKybVerificationId(KYB_VERIFICATION_ID);
        updatedVerification.setPartyId(PARTY_ID);
        updatedVerification.setVerificationStatus(VerificationStatusEnum.VERIFIED);
        updatedVerification.setVerificationDate(LocalDateTime.now());
        updatedVerification.setMercantileRegistryVerified(true);
        updatedVerification.setDeedOfIncorporationVerified(true);
        updatedVerification.setBusinessStructureVerified(true);
        updatedVerification.setUboVerified(true);
        updatedVerification.setTaxIdVerified(true);
        updatedVerification.setOperatingLicenseVerified(true);
        updatedVerification.setVerificationNotes("All verification steps completed");
        
        KybVerificationDTO updatedDTO = new KybVerificationDTO();
        updatedDTO.setKybVerificationId(KYB_VERIFICATION_ID);
        updatedDTO.setPartyId(PARTY_ID);
        updatedDTO.setVerificationStatus("VERIFIED");
        updatedDTO.setVerificationDate(LocalDateTime.now());
        updatedDTO.setMercantileRegistryVerified(true);
        updatedDTO.setDeedOfIncorporationVerified(true);
        updatedDTO.setBusinessStructureVerified(true);
        updatedDTO.setUboVerified(true);
        updatedDTO.setTaxIdVerified(true);
        updatedDTO.setOperatingLicenseVerified(true);
        updatedDTO.setVerificationNotes("All verification steps completed");
        
        when(repository.findById(KYB_VERIFICATION_ID)).thenReturn(Mono.just(existingVerification));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedVerification);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(KybVerification.class))).thenAnswer(invocation -> {
            KybVerification verification = invocation.getArgument(0);
            if (verification.getDateCreated() == null) {
                verification.setDateCreated(creationDate);
            }
            return Mono.just(verification);
        });
        
        when(mapper.toDTO(any(KybVerification.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(kybVerificationService.update(KYB_VERIFICATION_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(KYB_VERIFICATION_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(KybVerification.class));
        verify(mapper).toDTO(any(KybVerification.class));
        
        // Verify that the creation date is preserved
        verify(repository).save(argThat(verification -> 
            verification.getDateCreated() != null && verification.getDateCreated().equals(creationDate)));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(KYB_VERIFICATION_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(kybVerificationService.delete(KYB_VERIFICATION_ID))
                .verifyComplete();

        verify(repository).deleteById(KYB_VERIFICATION_ID);
    }
}