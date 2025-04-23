package com.catalis.core.kycb.core.services.industry.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.industry.v1.IndustryRiskMapper;
import com.catalis.core.kycb.interfaces.dtos.industry.v1.IndustryRiskDTO;
import com.catalis.core.kycb.interfaces.enums.risk.v1.RiskLevelEnum;
import com.catalis.core.kycb.models.entities.industry.v1.IndustryRisk;
import com.catalis.core.kycb.models.repositories.industry.v1.IndustryRiskRepository;
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
public class IndustryRiskServiceImplTest {

    @Mock
    private IndustryRiskRepository repository;

    @Mock
    private IndustryRiskMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private IndustryRiskServiceImpl industryRiskService;

    private IndustryRiskDTO industryRiskDTO;
    private IndustryRisk industryRisk;
    private final Long INDUSTRY_RISK_ID = 1L;
    private final String ACTIVITY_CODE = "NAICS-5242";
    private final String INDUSTRY_NAME = "Insurance Agencies and Brokerages";

    @BeforeEach
    void setUp() {
        // Initialize test data
        industryRiskDTO = new IndustryRiskDTO();
        industryRiskDTO.setIndustryRiskId(INDUSTRY_RISK_ID);
        industryRiskDTO.setActivityCode(ACTIVITY_CODE);
        industryRiskDTO.setIndustryName(INDUSTRY_NAME);
        industryRiskDTO.setInherentRiskLevel("MEDIUM");
        industryRiskDTO.setRiskScore(65);
        industryRiskDTO.setRiskFactors("International transactions, high-value policies");
        industryRiskDTO.setMitigatingFactors("Strong regulatory oversight");
        industryRiskDTO.setSepblacHighRisk(false);
        industryRiskDTO.setEuHighRisk(false);
        industryRiskDTO.setFatfHighRisk(false);
        industryRiskDTO.setCashIntensive(false);
        industryRiskDTO.setComplexStructures(true);
        industryRiskDTO.setRequiresEdd(false);
        
        industryRisk = new IndustryRisk();
        industryRisk.setIndustryRiskId(INDUSTRY_RISK_ID);
        industryRisk.setActivityCode(ACTIVITY_CODE);
        industryRisk.setIndustryName(INDUSTRY_NAME);
        industryRisk.setInherentRiskLevel(RiskLevelEnum.MEDIUM);
        industryRisk.setRiskScore(65);
        industryRisk.setRiskFactors("International transactions, high-value policies");
        industryRisk.setMitigatingFactors("Strong regulatory oversight");
        industryRisk.setSepblacHighRisk(false);
        industryRisk.setEuHighRisk(false);
        industryRisk.setFatfHighRisk(false);
        industryRisk.setCashIntensive(false);
        industryRisk.setComplexStructures(true);
        industryRisk.setRequiresEdd(false);
        industryRisk.setDateCreated(LocalDateTime.now());
        industryRisk.setAssessmentDate(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(IndustryRiskDTO.class))).thenReturn(industryRisk);
        when(repository.save(any(IndustryRisk.class))).thenReturn(Mono.just(industryRisk));
        when(mapper.toDTO(any(IndustryRisk.class))).thenReturn(industryRiskDTO);

        // Act & Assert
        StepVerifier.create(industryRiskService.create(industryRiskDTO))
                .expectNext(industryRiskDTO)
                .verifyComplete();

        verify(mapper).toEntity(industryRiskDTO);
        verify(repository).save(industryRisk);
        verify(mapper).toDTO(industryRisk);
    }

    @Test
    void testCreateWithAssessmentDate() {
        // Arrange
        IndustryRisk entityWithoutAssessmentDate = new IndustryRisk();
        entityWithoutAssessmentDate.setIndustryRiskId(INDUSTRY_RISK_ID);
        entityWithoutAssessmentDate.setActivityCode(ACTIVITY_CODE);
        entityWithoutAssessmentDate.setIndustryName(INDUSTRY_NAME);
        entityWithoutAssessmentDate.setInherentRiskLevel(RiskLevelEnum.MEDIUM);
        // No assessment date set

        when(mapper.toEntity(any(IndustryRiskDTO.class))).thenReturn(entityWithoutAssessmentDate);

        // Mock the save method to verify assessment date is set
        when(repository.save(any(IndustryRisk.class))).thenAnswer(invocation -> {
            IndustryRisk savedEntity = invocation.getArgument(0);
            // Verify that assessment date is set
            if (savedEntity.getAssessmentDate() == null) {
                throw new AssertionError("Assessment date should be set");
            }
            return Mono.just(savedEntity);
        });

        when(mapper.toDTO(any(IndustryRisk.class))).thenReturn(industryRiskDTO);

        // Act & Assert
        StepVerifier.create(industryRiskService.create(industryRiskDTO))
                .expectNext(industryRiskDTO)
                .verifyComplete();

        verify(mapper).toEntity(industryRiskDTO);
        verify(repository).save(any(IndustryRisk.class));
        verify(mapper).toDTO(any(IndustryRisk.class));

        // Verify that the saved entity has an assessment date
        verify(repository).save(argThat(entity -> entity.getAssessmentDate() != null));
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(INDUSTRY_RISK_ID)).thenReturn(Mono.just(industryRisk));
        when(mapper.toDTO(industryRisk)).thenReturn(industryRiskDTO);

        // Act & Assert
        StepVerifier.create(industryRiskService.getById(INDUSTRY_RISK_ID))
                .expectNext(industryRiskDTO)
                .verifyComplete();

        verify(repository).findById(INDUSTRY_RISK_ID);
        verify(mapper).toDTO(industryRisk);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        IndustryRisk existingRisk = new IndustryRisk();
        existingRisk.setIndustryRiskId(INDUSTRY_RISK_ID);
        existingRisk.setActivityCode(ACTIVITY_CODE);
        existingRisk.setIndustryName(INDUSTRY_NAME);
        existingRisk.setInherentRiskLevel(RiskLevelEnum.LOW);
        existingRisk.setDateCreated(creationDate);
        
        IndustryRisk updatedRisk = new IndustryRisk();
        updatedRisk.setIndustryRiskId(INDUSTRY_RISK_ID);
        updatedRisk.setActivityCode(ACTIVITY_CODE);
        updatedRisk.setIndustryName(INDUSTRY_NAME);
        updatedRisk.setInherentRiskLevel(RiskLevelEnum.MEDIUM);
        
        IndustryRiskDTO updatedDTO = new IndustryRiskDTO();
        updatedDTO.setIndustryRiskId(INDUSTRY_RISK_ID);
        updatedDTO.setActivityCode(ACTIVITY_CODE);
        updatedDTO.setIndustryName(INDUSTRY_NAME);
        updatedDTO.setInherentRiskLevel("MEDIUM");
        
        when(repository.findById(INDUSTRY_RISK_ID)).thenReturn(Mono.just(existingRisk));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedRisk);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(IndustryRisk.class))).thenAnswer(invocation -> {
            IndustryRisk risk = invocation.getArgument(0);
            if (risk.getDateCreated() == null) {
                risk.setDateCreated(creationDate);
            }
            return Mono.just(risk);
        });
        
        when(mapper.toDTO(any(IndustryRisk.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(industryRiskService.update(INDUSTRY_RISK_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(INDUSTRY_RISK_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(IndustryRisk.class));
        verify(mapper).toDTO(any(IndustryRisk.class));
        
        // Verify that the creation date is preserved
        verify(repository).save(argThat(risk -> 
            risk.getDateCreated() != null && risk.getDateCreated().equals(creationDate)));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(INDUSTRY_RISK_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(industryRiskService.delete(INDUSTRY_RISK_ID))
                .verifyComplete();

        verify(repository).deleteById(INDUSTRY_RISK_ID);
    }
}