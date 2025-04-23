package com.catalis.core.kycb.core.services.kyc.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.kyc.v1.KycVerificationMapper;
import com.catalis.core.kycb.interfaces.dtos.kyc.v1.KycVerificationDTO;
import com.catalis.core.kycb.models.entities.kyc.v1.KycVerification;
import com.catalis.core.kycb.models.repositories.kyc.v1.KycVerificationRepository;
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
public class KycVerificationServiceImplTest {

    @Mock
    private KycVerificationRepository repository;

    @Mock
    private KycVerificationMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private KycVerificationServiceImpl kycVerificationService;

    private KycVerificationDTO kycVerificationDTO;
    private KycVerification kycVerification;
    private final Long KYC_VERIFICATION_ID = 1L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        kycVerificationDTO = new KycVerificationDTO();
        kycVerificationDTO.setKycVerificationId(KYC_VERIFICATION_ID);
        
        kycVerification = new KycVerification();
        kycVerification.setKycVerificationId(KYC_VERIFICATION_ID);
        kycVerification.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(KycVerificationDTO.class))).thenReturn(kycVerification);
        when(repository.save(any(KycVerification.class))).thenReturn(Mono.just(kycVerification));
        when(mapper.toDTO(any(KycVerification.class))).thenReturn(kycVerificationDTO);

        // Act & Assert
        StepVerifier.create(kycVerificationService.create(kycVerificationDTO))
                .expectNext(kycVerificationDTO)
                .verifyComplete();

        verify(mapper).toEntity(kycVerificationDTO);
        verify(repository).save(kycVerification);
        verify(mapper).toDTO(kycVerification);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(KYC_VERIFICATION_ID)).thenReturn(Mono.just(kycVerification));
        when(mapper.toDTO(kycVerification)).thenReturn(kycVerificationDTO);

        // Act & Assert
        StepVerifier.create(kycVerificationService.getById(KYC_VERIFICATION_ID))
                .expectNext(kycVerificationDTO)
                .verifyComplete();

        verify(repository).findById(KYC_VERIFICATION_ID);
        verify(mapper).toDTO(kycVerification);
    }

    @Test
    void testUpdate() {
        // Arrange
        when(repository.findById(KYC_VERIFICATION_ID)).thenReturn(Mono.just(kycVerification));
        when(mapper.toEntity(kycVerificationDTO)).thenReturn(kycVerification);
        when(repository.save(kycVerification)).thenReturn(Mono.just(kycVerification));
        when(mapper.toDTO(kycVerification)).thenReturn(kycVerificationDTO);

        // Act & Assert
        StepVerifier.create(kycVerificationService.update(KYC_VERIFICATION_ID, kycVerificationDTO))
                .expectNext(kycVerificationDTO)
                .verifyComplete();

        verify(repository).findById(KYC_VERIFICATION_ID);
        verify(mapper).toEntity(kycVerificationDTO);
        verify(repository).save(kycVerification);
        verify(mapper).toDTO(kycVerification);
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(KYC_VERIFICATION_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(kycVerificationService.delete(KYC_VERIFICATION_ID))
                .verifyComplete();

        verify(repository).deleteById(KYC_VERIFICATION_ID);
    }
}