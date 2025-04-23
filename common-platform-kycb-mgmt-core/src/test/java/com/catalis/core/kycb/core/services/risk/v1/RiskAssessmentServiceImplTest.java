package com.catalis.core.kycb.core.services.risk.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.risk.v1.RiskAssessmentMapper;
import com.catalis.core.kycb.interfaces.dtos.risk.v1.RiskAssessmentDTO;
import com.catalis.core.kycb.interfaces.enums.assessment.v1.AssessmentTypeEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskCategoryEnum;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.models.entities.risk.v1.RiskAssessment;
import com.catalis.core.kycb.models.repositories.risk.v1.RiskAssessmentRepository;
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
public class RiskAssessmentServiceImplTest {

    @Mock
    private RiskAssessmentRepository repository;

    @Mock
    private RiskAssessmentMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private RiskAssessmentServiceImpl riskAssessmentService;

    private RiskAssessmentDTO riskAssessmentDTO;
    private RiskAssessment riskAssessment;
    private final Long RISK_ASSESSMENT_ID = 1L;
    private final Long PARTY_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        riskAssessmentDTO = new RiskAssessmentDTO();
        riskAssessmentDTO.setRiskAssessmentId(RISK_ASSESSMENT_ID);
        riskAssessmentDTO.setPartyId(PARTY_ID);
        riskAssessmentDTO.setAssessmentType("INITIAL");
        riskAssessmentDTO.setAssessmentDate(LocalDateTime.now());
        riskAssessmentDTO.setRiskCategory("CUSTOMER");
        riskAssessmentDTO.setRiskScore(75);
        riskAssessmentDTO.setRiskLevel("HIGH");
        riskAssessmentDTO.setRiskFactors("High-risk jurisdiction, complex ownership structure");
        riskAssessmentDTO.setAssessmentNotes("Initial assessment based on onboarding information");
        riskAssessmentDTO.setAssessmentAgent("John Doe");
        riskAssessmentDTO.setNextAssessmentDate(LocalDateTime.now().plusMonths(6));
        
        riskAssessment = new RiskAssessment();
        riskAssessment.setRiskAssessmentId(RISK_ASSESSMENT_ID);
        riskAssessment.setPartyId(PARTY_ID);
        riskAssessment.setAssessmentType(AssessmentTypeEnum.INITIAL);
        riskAssessment.setAssessmentDate(LocalDateTime.now());
        riskAssessment.setRiskCategory(RiskCategoryEnum.CUSTOMER);
        riskAssessment.setRiskScore(75);
        riskAssessment.setRiskLevel(RiskLevelEnum.HIGH);
        riskAssessment.setRiskFactors("High-risk jurisdiction, complex ownership structure");
        riskAssessment.setAssessmentNotes("Initial assessment based on onboarding information");
        riskAssessment.setAssessmentAgent("John Doe");
        riskAssessment.setNextAssessmentDate(LocalDateTime.now().plusMonths(6));
        riskAssessment.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(RiskAssessmentDTO.class))).thenReturn(riskAssessment);
        when(repository.save(any(RiskAssessment.class))).thenReturn(Mono.just(riskAssessment));
        when(mapper.toDTO(any(RiskAssessment.class))).thenReturn(riskAssessmentDTO);

        // Act & Assert
        StepVerifier.create(riskAssessmentService.create(riskAssessmentDTO))
                .expectNext(riskAssessmentDTO)
                .verifyComplete();

        verify(mapper).toEntity(riskAssessmentDTO);
        verify(repository).save(riskAssessment);
        verify(mapper).toDTO(riskAssessment);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(RISK_ASSESSMENT_ID)).thenReturn(Mono.just(riskAssessment));
        when(mapper.toDTO(riskAssessment)).thenReturn(riskAssessmentDTO);

        // Act & Assert
        StepVerifier.create(riskAssessmentService.getById(RISK_ASSESSMENT_ID))
                .expectNext(riskAssessmentDTO)
                .verifyComplete();

        verify(repository).findById(RISK_ASSESSMENT_ID);
        verify(mapper).toDTO(riskAssessment);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        RiskAssessment existingAssessment = new RiskAssessment();
        existingAssessment.setRiskAssessmentId(RISK_ASSESSMENT_ID);
        existingAssessment.setPartyId(PARTY_ID);
        existingAssessment.setAssessmentType(AssessmentTypeEnum.INITIAL);
        existingAssessment.setRiskLevel(RiskLevelEnum.MEDIUM);
        existingAssessment.setDateCreated(creationDate);
        
        RiskAssessment updatedAssessment = new RiskAssessment();
        updatedAssessment.setRiskAssessmentId(RISK_ASSESSMENT_ID);
        updatedAssessment.setPartyId(PARTY_ID);
        updatedAssessment.setAssessmentType(AssessmentTypeEnum.PERIODIC);
        updatedAssessment.setRiskLevel(RiskLevelEnum.HIGH);
        updatedAssessment.setRiskScore(75);
        updatedAssessment.setAssessmentDate(LocalDateTime.now());
        
        RiskAssessmentDTO updatedDTO = new RiskAssessmentDTO();
        updatedDTO.setRiskAssessmentId(RISK_ASSESSMENT_ID);
        updatedDTO.setPartyId(PARTY_ID);
        updatedDTO.setAssessmentType("PERIODIC");
        updatedDTO.setRiskLevel("HIGH");
        updatedDTO.setRiskScore(75);
        updatedDTO.setAssessmentDate(LocalDateTime.now());
        
        when(repository.findById(RISK_ASSESSMENT_ID)).thenReturn(Mono.just(existingAssessment));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedAssessment);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(RiskAssessment.class))).thenAnswer(invocation -> {
            RiskAssessment assessment = invocation.getArgument(0);
            if (assessment.getDateCreated() == null) {
                assessment.setDateCreated(creationDate);
            }
            return Mono.just(assessment);
        });
        
        when(mapper.toDTO(any(RiskAssessment.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(riskAssessmentService.update(RISK_ASSESSMENT_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(RISK_ASSESSMENT_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(RiskAssessment.class));
        verify(mapper).toDTO(any(RiskAssessment.class));
        
        // Verify that the creation date is preserved
        verify(repository).save(argThat(assessment -> 
            assessment.getDateCreated() != null && assessment.getDateCreated().equals(creationDate)));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(RISK_ASSESSMENT_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(riskAssessmentService.delete(RISK_ASSESSMENT_ID))
                .verifyComplete();

        verify(repository).deleteById(RISK_ASSESSMENT_ID);
    }
}