package com.catalis.core.kycb.core.services.compliance.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.compliance.v1.ComplianceActionMapper;
import com.catalis.core.kycb.interfaces.dtos.compliance.v1.ComplianceActionDTO;
import com.catalis.core.kycb.models.entities.compliance.v1.ComplianceAction;
import com.catalis.core.kycb.models.repositories.compliance.v1.ComplianceActionRepository;
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
public class ComplianceActionServiceImplTest {

    @Mock
    private ComplianceActionRepository repository;

    @Mock
    private ComplianceActionMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private ComplianceActionServiceImpl complianceActionService;

    private ComplianceActionDTO complianceActionDTO;
    private ComplianceAction complianceAction;
    private final Long COMPLIANCE_ACTION_ID = 1L;
    private final Long COMPLIANCE_CASE_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        complianceActionDTO = new ComplianceActionDTO();
        complianceActionDTO.setComplianceActionId(COMPLIANCE_ACTION_ID);
        complianceActionDTO.setComplianceCaseId(COMPLIANCE_CASE_ID);
        
        complianceAction = new ComplianceAction();
        complianceAction.setComplianceActionId(COMPLIANCE_ACTION_ID);
        complianceAction.setComplianceCaseId(COMPLIANCE_CASE_ID);
        complianceAction.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(ComplianceActionDTO.class))).thenReturn(complianceAction);
        when(repository.save(any(ComplianceAction.class))).thenReturn(Mono.just(complianceAction));
        when(mapper.toDTO(any(ComplianceAction.class))).thenReturn(complianceActionDTO);

        // Act & Assert
        StepVerifier.create(complianceActionService.create(complianceActionDTO))
                .expectNext(complianceActionDTO)
                .verifyComplete();

        verify(mapper).toEntity(complianceActionDTO);
        verify(repository).save(complianceAction);
        verify(mapper).toDTO(complianceAction);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(COMPLIANCE_ACTION_ID)).thenReturn(Mono.just(complianceAction));
        when(mapper.toDTO(complianceAction)).thenReturn(complianceActionDTO);

        // Act & Assert
        StepVerifier.create(complianceActionService.getById(COMPLIANCE_ACTION_ID))
                .expectNext(complianceActionDTO)
                .verifyComplete();

        verify(repository).findById(COMPLIANCE_ACTION_ID);
        verify(mapper).toDTO(complianceAction);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        ComplianceAction existingAction = new ComplianceAction();
        existingAction.setComplianceActionId(COMPLIANCE_ACTION_ID);
        existingAction.setComplianceCaseId(COMPLIANCE_CASE_ID);
        existingAction.setDateCreated(creationDate);
        
        ComplianceAction updatedAction = new ComplianceAction();
        updatedAction.setComplianceActionId(COMPLIANCE_ACTION_ID);
        updatedAction.setComplianceCaseId(COMPLIANCE_CASE_ID);
        
        when(repository.findById(COMPLIANCE_ACTION_ID)).thenReturn(Mono.just(existingAction));
        when(mapper.toEntity(complianceActionDTO)).thenReturn(updatedAction);
        
        // Mock the save method to set the creation date before returning
        when(repository.save(any(ComplianceAction.class))).thenAnswer(invocation -> {
            ComplianceAction action = invocation.getArgument(0);
            if (action.getDateCreated() == null) {
                action.setDateCreated(creationDate);
            }
            return Mono.just(action);
        });
        
        when(mapper.toDTO(any(ComplianceAction.class))).thenReturn(complianceActionDTO);

        // Act & Assert
        StepVerifier.create(complianceActionService.update(COMPLIANCE_ACTION_ID, complianceActionDTO))
                .expectNext(complianceActionDTO)
                .verifyComplete();

        verify(repository).findById(COMPLIANCE_ACTION_ID);
        verify(mapper).toEntity(complianceActionDTO);
        verify(repository).save(any(ComplianceAction.class));
        verify(mapper).toDTO(any(ComplianceAction.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(COMPLIANCE_ACTION_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(complianceActionService.delete(COMPLIANCE_ACTION_ID))
                .verifyComplete();

        verify(repository).deleteById(COMPLIANCE_ACTION_ID);
    }
}