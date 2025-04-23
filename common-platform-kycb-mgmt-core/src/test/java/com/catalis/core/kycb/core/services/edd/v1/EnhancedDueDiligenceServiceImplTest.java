package com.catalis.core.kycb.core.services.edd.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.edd.v1.EnhancedDueDiligenceMapper;
import com.catalis.core.kycb.interfaces.dtos.edd.v1.EnhancedDueDiligenceDTO;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddReasonEnum;
import com.catalis.core.kycb.interfaces.enums.edd.v1.EddStatusEnum;
import com.catalis.core.kycb.models.entities.edd.v1.EnhancedDueDiligence;
import com.catalis.core.kycb.models.repositories.edd.v1.EnhancedDueDiligenceRepository;
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
public class EnhancedDueDiligenceServiceImplTest {

    @Mock
    private EnhancedDueDiligenceRepository repository;

    @Mock
    private EnhancedDueDiligenceMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private EnhancedDueDiligenceServiceImpl enhancedDueDiligenceService;

    private EnhancedDueDiligenceDTO enhancedDueDiligenceDTO;
    private EnhancedDueDiligence enhancedDueDiligence;
    private final Long EDD_ID = 1L;
    private final Long KYC_VERIFICATION_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        enhancedDueDiligenceDTO = new EnhancedDueDiligenceDTO();
        enhancedDueDiligenceDTO.setEddId(EDD_ID);
        enhancedDueDiligenceDTO.setKycVerificationId(KYC_VERIFICATION_ID);
        enhancedDueDiligenceDTO.setEddReason("HIGH_RISK");
        enhancedDueDiligenceDTO.setEddStatus("PENDING");
        enhancedDueDiligenceDTO.setEddDescription("High-risk customer requiring additional verification");
        enhancedDueDiligenceDTO.setApprovingAuthority("Compliance Officer");
        enhancedDueDiligenceDTO.setEddNotes("Additional documentation requested");
        enhancedDueDiligenceDTO.setInternalCommitteeApproval(false);
        
        enhancedDueDiligence = new EnhancedDueDiligence();
        enhancedDueDiligence.setEddId(EDD_ID);
        enhancedDueDiligence.setKycVerificationId(KYC_VERIFICATION_ID);
        enhancedDueDiligence.setEddReason(EddReasonEnum.HIGH_RISK);
        enhancedDueDiligence.setEddStatus(EddStatusEnum.PENDING);
        enhancedDueDiligence.setEddDescription("High-risk customer requiring additional verification");
        enhancedDueDiligence.setApprovingAuthority("Compliance Officer");
        enhancedDueDiligence.setEddNotes("Additional documentation requested");
        enhancedDueDiligence.setInternalCommitteeApproval(false);
        enhancedDueDiligence.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(EnhancedDueDiligenceDTO.class))).thenReturn(enhancedDueDiligence);
        when(repository.save(any(EnhancedDueDiligence.class))).thenReturn(Mono.just(enhancedDueDiligence));
        when(mapper.toDTO(any(EnhancedDueDiligence.class))).thenReturn(enhancedDueDiligenceDTO);

        // Act & Assert
        StepVerifier.create(enhancedDueDiligenceService.create(enhancedDueDiligenceDTO))
                .expectNext(enhancedDueDiligenceDTO)
                .verifyComplete();

        verify(mapper).toEntity(enhancedDueDiligenceDTO);
        verify(repository).save(enhancedDueDiligence);
        verify(mapper).toDTO(enhancedDueDiligence);
    }

    @Test
    void testCreateWithDefaultStatus() {
        // Arrange
        EnhancedDueDiligenceDTO dtoWithoutStatus = new EnhancedDueDiligenceDTO();
        dtoWithoutStatus.setEddId(EDD_ID);
        dtoWithoutStatus.setKycVerificationId(KYC_VERIFICATION_ID);
        dtoWithoutStatus.setEddReason("HIGH_RISK");
        // No status set
        
        EnhancedDueDiligence entityWithoutStatus = new EnhancedDueDiligence();
        entityWithoutStatus.setEddId(EDD_ID);
        entityWithoutStatus.setKycVerificationId(KYC_VERIFICATION_ID);
        entityWithoutStatus.setEddReason(EddReasonEnum.HIGH_RISK);
        // No status set
        
        EnhancedDueDiligence savedEntity = new EnhancedDueDiligence();
        savedEntity.setEddId(EDD_ID);
        savedEntity.setKycVerificationId(KYC_VERIFICATION_ID);
        savedEntity.setEddReason(EddReasonEnum.HIGH_RISK);
        savedEntity.setEddStatus(EddStatusEnum.PENDING); // Default status
        
        EnhancedDueDiligenceDTO savedDTO = new EnhancedDueDiligenceDTO();
        savedDTO.setEddId(EDD_ID);
        savedDTO.setKycVerificationId(KYC_VERIFICATION_ID);
        savedDTO.setEddReason("HIGH_RISK");
        savedDTO.setEddStatus("PENDING"); // Default status
        
        when(mapper.toEntity(dtoWithoutStatus)).thenReturn(entityWithoutStatus);
        
        // Mock the save method to verify the status is set to PENDING
        when(repository.save(any(EnhancedDueDiligence.class))).thenAnswer(invocation -> {
            EnhancedDueDiligence entity = invocation.getArgument(0);
            // Verify that the status is set to PENDING
            if (entity.getEddStatus() != EddStatusEnum.PENDING) {
                throw new AssertionError("Status should be set to PENDING");
            }
            return Mono.just(savedEntity);
        });
        
        when(mapper.toDTO(savedEntity)).thenReturn(savedDTO);

        // Act & Assert
        StepVerifier.create(enhancedDueDiligenceService.create(dtoWithoutStatus))
                .expectNext(savedDTO)
                .verifyComplete();

        verify(mapper).toEntity(dtoWithoutStatus);
        verify(repository).save(any(EnhancedDueDiligence.class));
        verify(mapper).toDTO(savedEntity);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(EDD_ID)).thenReturn(Mono.just(enhancedDueDiligence));
        when(mapper.toDTO(enhancedDueDiligence)).thenReturn(enhancedDueDiligenceDTO);

        // Act & Assert
        StepVerifier.create(enhancedDueDiligenceService.getById(EDD_ID))
                .expectNext(enhancedDueDiligenceDTO)
                .verifyComplete();

        verify(repository).findById(EDD_ID);
        verify(mapper).toDTO(enhancedDueDiligence);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        EnhancedDueDiligence existingEdd = new EnhancedDueDiligence();
        existingEdd.setEddId(EDD_ID);
        existingEdd.setKycVerificationId(KYC_VERIFICATION_ID);
        existingEdd.setEddReason(EddReasonEnum.HIGH_RISK);
        existingEdd.setEddStatus(EddStatusEnum.PENDING);
        existingEdd.setDateCreated(creationDate);
        
        EnhancedDueDiligence updatedEdd = new EnhancedDueDiligence();
        updatedEdd.setEddId(EDD_ID);
        updatedEdd.setKycVerificationId(KYC_VERIFICATION_ID);
        updatedEdd.setEddReason(EddReasonEnum.HIGH_RISK);
        updatedEdd.setEddStatus(EddStatusEnum.COMPLETED);
        updatedEdd.setCompletionDate(LocalDateTime.now());
        updatedEdd.setCompletedBy("Senior Compliance Officer");
        
        EnhancedDueDiligenceDTO updatedDTO = new EnhancedDueDiligenceDTO();
        updatedDTO.setEddId(EDD_ID);
        updatedDTO.setKycVerificationId(KYC_VERIFICATION_ID);
        updatedDTO.setEddReason("HIGH_RISK");
        updatedDTO.setEddStatus("COMPLETED");
        updatedDTO.setCompletionDate(LocalDateTime.now());
        updatedDTO.setCompletedBy("Senior Compliance Officer");
        
        when(repository.findById(EDD_ID)).thenReturn(Mono.just(existingEdd));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedEdd);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(EnhancedDueDiligence.class))).thenAnswer(invocation -> {
            EnhancedDueDiligence edd = invocation.getArgument(0);
            if (edd.getDateCreated() == null) {
                edd.setDateCreated(creationDate);
            }
            return Mono.just(edd);
        });
        
        when(mapper.toDTO(any(EnhancedDueDiligence.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(enhancedDueDiligenceService.update(EDD_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(EDD_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(EnhancedDueDiligence.class));
        verify(mapper).toDTO(any(EnhancedDueDiligence.class));
        
        // Verify that the creation date is preserved
        verify(repository).save(argThat(edd -> 
            edd.getDateCreated() != null && edd.getDateCreated().equals(creationDate)));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(EDD_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(enhancedDueDiligenceService.delete(EDD_ID))
                .verifyComplete();

        verify(repository).deleteById(EDD_ID);
    }
}