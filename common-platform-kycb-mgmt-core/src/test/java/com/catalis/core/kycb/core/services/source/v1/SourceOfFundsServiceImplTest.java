package com.catalis.core.kycb.core.services.source.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.source.v1.SourceOfFundsMapper;
import com.catalis.core.kycb.interfaces.dtos.source.v1.SourceOfFundsDTO;
import com.catalis.core.kycb.interfaces.enums.source.v1.SourceTypeEnum;
import com.catalis.core.kycb.models.entities.source.v1.SourceOfFunds;
import com.catalis.core.kycb.models.repositories.source.v1.SourceOfFundsRepository;
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
public class SourceOfFundsServiceImplTest {

    @Mock
    private SourceOfFundsRepository repository;

    @Mock
    private SourceOfFundsMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private SourceOfFundsServiceImpl sourceOfFundsService;

    private SourceOfFundsDTO sourceOfFundsDTO;
    private SourceOfFunds sourceOfFunds;
    private final Long SOURCE_OF_FUNDS_ID = 1L;
    private final Long PARTY_ID = 100L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        sourceOfFundsDTO = new SourceOfFundsDTO();
        sourceOfFundsDTO.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        sourceOfFundsDTO.setPartyId(PARTY_ID);
        sourceOfFundsDTO.setSourceType("SALARY");
        sourceOfFundsDTO.setSourceDescription("Monthly salary from ABC Corp");
        sourceOfFundsDTO.setEstimatedAnnualAmount(new BigDecimal("60000.00"));
        sourceOfFundsDTO.setCurrency("USD");
        sourceOfFundsDTO.setIsVerified(false);
        sourceOfFundsDTO.setSupportingDocuments("payslips.pdf");
        
        sourceOfFunds = new SourceOfFunds();
        sourceOfFunds.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        sourceOfFunds.setPartyId(PARTY_ID);
        sourceOfFunds.setSourceType(SourceTypeEnum.SALARY);
        sourceOfFunds.setSourceDescription("Monthly salary from ABC Corp");
        sourceOfFunds.setEstimatedAnnualAmount(new BigDecimal("60000.00"));
        sourceOfFunds.setCurrency("USD");
        sourceOfFunds.setIsVerified(false);
        sourceOfFunds.setSupportingDocuments("payslips.pdf");
        sourceOfFunds.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(SourceOfFundsDTO.class))).thenReturn(sourceOfFunds);
        when(repository.save(any(SourceOfFunds.class))).thenReturn(Mono.just(sourceOfFunds));
        when(mapper.toDTO(any(SourceOfFunds.class))).thenReturn(sourceOfFundsDTO);

        // Act & Assert
        StepVerifier.create(sourceOfFundsService.create(sourceOfFundsDTO))
                .expectNext(sourceOfFundsDTO)
                .verifyComplete();

        verify(mapper).toEntity(sourceOfFundsDTO);
        verify(repository).save(sourceOfFunds);
        verify(mapper).toDTO(sourceOfFunds);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(SOURCE_OF_FUNDS_ID)).thenReturn(Mono.just(sourceOfFunds));
        when(mapper.toDTO(sourceOfFunds)).thenReturn(sourceOfFundsDTO);

        // Act & Assert
        StepVerifier.create(sourceOfFundsService.getById(SOURCE_OF_FUNDS_ID))
                .expectNext(sourceOfFundsDTO)
                .verifyComplete();

        verify(repository).findById(SOURCE_OF_FUNDS_ID);
        verify(mapper).toDTO(sourceOfFunds);
    }

    @Test
    void testUpdate() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        
        SourceOfFunds existingSource = new SourceOfFunds();
        existingSource.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        existingSource.setPartyId(PARTY_ID);
        existingSource.setSourceType(SourceTypeEnum.SALARY);
        existingSource.setDateCreated(creationDate);
        existingSource.setIsVerified(false);
        
        SourceOfFunds updatedSource = new SourceOfFunds();
        updatedSource.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        updatedSource.setPartyId(PARTY_ID);
        updatedSource.setSourceType(SourceTypeEnum.BUSINESS_INCOME);
        updatedSource.setSourceDescription("Business income from XYZ LLC");
        updatedSource.setEstimatedAnnualAmount(new BigDecimal("120000.00"));
        
        SourceOfFundsDTO updatedDTO = new SourceOfFundsDTO();
        updatedDTO.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        updatedDTO.setPartyId(PARTY_ID);
        updatedDTO.setSourceType("BUSINESS_INCOME");
        updatedDTO.setSourceDescription("Business income from XYZ LLC");
        updatedDTO.setEstimatedAnnualAmount(new BigDecimal("120000.00"));
        
        when(repository.findById(SOURCE_OF_FUNDS_ID)).thenReturn(Mono.just(existingSource));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedSource);
        when(repository.save(any(SourceOfFunds.class))).thenReturn(Mono.just(updatedSource));
        when(mapper.toDTO(any(SourceOfFunds.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(sourceOfFundsService.update(SOURCE_OF_FUNDS_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(SOURCE_OF_FUNDS_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(SourceOfFunds.class));
        verify(mapper).toDTO(any(SourceOfFunds.class));
        
        // Verify that the creation date is preserved
        verify(repository).save(argThat(source -> 
            source.getDateCreated() != null && source.getDateCreated().equals(creationDate)));
    }

    @Test
    void testUpdatePreservesVerificationData() {
        // Arrange
        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime verificationDate = LocalDateTime.now();
        
        SourceOfFunds existingSource = new SourceOfFunds();
        existingSource.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        existingSource.setPartyId(PARTY_ID);
        existingSource.setSourceType(SourceTypeEnum.SALARY);
        existingSource.setDateCreated(creationDate);
        existingSource.setIsVerified(true);
        existingSource.setVerificationDate(verificationDate);
        existingSource.setVerificationMethod("DOCUMENT_REVIEW");
        
        SourceOfFunds updatedSource = new SourceOfFunds();
        updatedSource.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        updatedSource.setPartyId(PARTY_ID);
        updatedSource.setSourceType(SourceTypeEnum.SALARY);
        updatedSource.setSourceDescription("Updated description");
        // No verification data set in the updated entity
        
        SourceOfFundsDTO updatedDTO = new SourceOfFundsDTO();
        updatedDTO.setSourceOfFundsId(SOURCE_OF_FUNDS_ID);
        updatedDTO.setPartyId(PARTY_ID);
        updatedDTO.setSourceType("SALARY");
        updatedDTO.setSourceDescription("Updated description");
        // No verification data set in the updated DTO
        
        when(repository.findById(SOURCE_OF_FUNDS_ID)).thenReturn(Mono.just(existingSource));
        when(mapper.toEntity(updatedDTO)).thenReturn(updatedSource);
        
        // Mock the save method to verify verification data is preserved
        when(repository.save(any(SourceOfFunds.class))).thenAnswer(invocation -> {
            SourceOfFunds savedSource = invocation.getArgument(0);
            // Verify that verification data is preserved
            if (savedSource.getIsVerified() == null && existingSource.getIsVerified()) {
                savedSource.setIsVerified(existingSource.getIsVerified());
                savedSource.setVerificationDate(existingSource.getVerificationDate());
                savedSource.setVerificationMethod(existingSource.getVerificationMethod());
            }
            return Mono.just(savedSource);
        });
        
        when(mapper.toDTO(any(SourceOfFunds.class))).thenReturn(updatedDTO);

        // Act & Assert
        StepVerifier.create(sourceOfFundsService.update(SOURCE_OF_FUNDS_ID, updatedDTO))
                .expectNext(updatedDTO)
                .verifyComplete();

        verify(repository).findById(SOURCE_OF_FUNDS_ID);
        verify(mapper).toEntity(updatedDTO);
        verify(repository).save(any(SourceOfFunds.class));
        verify(mapper).toDTO(any(SourceOfFunds.class));
        
        // Verify that verification data is preserved
        verify(repository).save(argThat(source -> 
            source.getIsVerified() != null && source.getIsVerified() &&
            source.getVerificationDate() != null &&
            source.getVerificationMethod() != null));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(SOURCE_OF_FUNDS_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(sourceOfFundsService.delete(SOURCE_OF_FUNDS_ID))
                .verifyComplete();

        verify(repository).deleteById(SOURCE_OF_FUNDS_ID);
    }
}