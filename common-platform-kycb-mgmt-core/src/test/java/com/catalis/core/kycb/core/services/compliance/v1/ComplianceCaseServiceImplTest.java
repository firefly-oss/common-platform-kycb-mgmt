package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.compliance.v1.ComplianceCaseMapper;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceCaseDTO;
import com.catalis.core.kycb.models.entities.compliance.v1.ComplianceCase;
import com.catalis.core.kycb.models.repositories.compliance.v1.ComplianceCaseRepository;
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
public class ComplianceCaseServiceImplTest {

    @Mock
    private ComplianceCaseRepository repository;

    @Mock
    private ComplianceCaseMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private ComplianceCaseServiceImpl complianceCaseService;

    private ComplianceCaseDTO complianceCaseDTO;
    private ComplianceCase complianceCase;
    private final Long COMPLIANCE_CASE_ID = 1L;
    private final Long PARTY_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        complianceCaseDTO = new ComplianceCaseDTO();
        complianceCaseDTO.setComplianceCaseId(COMPLIANCE_CASE_ID);
        complianceCaseDTO.setPartyId(PARTY_ID);
        
        complianceCase = new ComplianceCase();
        complianceCase.setComplianceCaseId(COMPLIANCE_CASE_ID);
        complianceCase.setPartyId(PARTY_ID);
        complianceCase.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(ComplianceCaseDTO.class))).thenReturn(complianceCase);
        when(repository.save(any(ComplianceCase.class))).thenReturn(Mono.just(complianceCase));
        when(mapper.toDTO(any(ComplianceCase.class))).thenReturn(complianceCaseDTO);

        // Act & Assert
        StepVerifier.create(complianceCaseService.create(complianceCaseDTO))
                .expectNext(complianceCaseDTO)
                .verifyComplete();

        verify(mapper).toEntity(complianceCaseDTO);
        verify(repository).save(complianceCase);
        verify(mapper).toDTO(complianceCase);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(COMPLIANCE_CASE_ID)).thenReturn(Mono.just(complianceCase));
        when(mapper.toDTO(complianceCase)).thenReturn(complianceCaseDTO);

        // Act & Assert
        StepVerifier.create(complianceCaseService.getById(COMPLIANCE_CASE_ID))
                .expectNext(complianceCaseDTO)
                .verifyComplete();

        verify(repository).findById(COMPLIANCE_CASE_ID);
        verify(mapper).toDTO(complianceCase);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        ComplianceCase existingCase = new ComplianceCase();
        existingCase.setComplianceCaseId(COMPLIANCE_CASE_ID);
        existingCase.setPartyId(PARTY_ID);
        existingCase.setDateCreated(creationDate);
        
        ComplianceCase updatedCase = new ComplianceCase();
        updatedCase.setComplianceCaseId(COMPLIANCE_CASE_ID);
        updatedCase.setPartyId(PARTY_ID);
        
        when(repository.findById(COMPLIANCE_CASE_ID)).thenReturn(Mono.just(existingCase));
        when(mapper.toEntity(complianceCaseDTO)).thenReturn(updatedCase);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(ComplianceCase.class))).thenAnswer(invocation -> {
            ComplianceCase caseEntity = invocation.getArgument(0);
            if (caseEntity.getDateCreated() == null) {
                caseEntity.setDateCreated(creationDate);
            }
            return Mono.just(caseEntity);
        });
        
        when(mapper.toDTO(any(ComplianceCase.class))).thenReturn(complianceCaseDTO);

        // Act & Assert
        StepVerifier.create(complianceCaseService.update(COMPLIANCE_CASE_ID, complianceCaseDTO))
                .expectNext(complianceCaseDTO)
                .verifyComplete();

        verify(repository).findById(COMPLIANCE_CASE_ID);
        verify(mapper).toEntity(complianceCaseDTO);
        verify(repository).save(any(ComplianceCase.class));
        verify(mapper).toDTO(any(ComplianceCase.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(COMPLIANCE_CASE_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(complianceCaseService.delete(COMPLIANCE_CASE_ID))
                .verifyComplete();

        verify(repository).deleteById(COMPLIANCE_CASE_ID);
    }
}