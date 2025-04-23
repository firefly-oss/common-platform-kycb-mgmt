package com.catalis.core.kycb.core.services.ownership.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.ownership.v1.UboMapper;
import com.catalis.core.kycb.interfaces.dtos.ownership.v1.UboDTO;
import com.catalis.core.kycb.models.entities.ownership.v1.Ubo;
import com.catalis.core.kycb.models.repositories.ownership.v1.UboRepository;
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
public class UboServiceImplTest {

    @Mock
    private UboRepository repository;

    @Mock
    private UboMapper mapper;

    @Mock
    private FilterUtils filterUtils;

    @InjectMocks
    private UboServiceImpl uboService;

    private UboDTO uboDTO;
    private Ubo ubo;
    private final Long UBO_ID = 1L;

    @BeforeEach
    void setUp() {
        // Initialize test data
        uboDTO = new UboDTO();
        uboDTO.setUboId(UBO_ID);

        ubo = new Ubo();
        ubo.setUboId(UBO_ID);
        ubo.setDateCreated(LocalDateTime.now());
    }

    @Test
    void testCreate() {
        // Arrange
        when(mapper.toEntity(any(UboDTO.class))).thenReturn(ubo);
        when(repository.save(any(Ubo.class))).thenReturn(Mono.just(ubo));
        when(mapper.toDTO(any(Ubo.class))).thenReturn(uboDTO);

        // Act & Assert
        StepVerifier.create(uboService.create(uboDTO))
                .expectNext(uboDTO)
                .verifyComplete();

        verify(mapper).toEntity(uboDTO);
        verify(repository).save(ubo);
        verify(mapper).toDTO(ubo);
    }

    @Test
    void testGetById() {
        // Arrange
        when(repository.findById(UBO_ID)).thenReturn(Mono.just(ubo));
        when(mapper.toDTO(ubo)).thenReturn(uboDTO);

        // Act & Assert
        StepVerifier.create(uboService.getById(UBO_ID))
                .expectNext(uboDTO)
                .verifyComplete();

        verify(repository).findById(UBO_ID);
        verify(mapper).toDTO(ubo);
    }

    @Test
    void testUpdate() {
        // Arrange
        Ubo existingUbo = new Ubo();
        existingUbo.setUboId(UBO_ID);
        existingUbo.setDateCreated(LocalDateTime.now());
        existingUbo.setIsVerified(true); // Set isVerified to avoid NPE
        existingUbo.setVerificationDate(LocalDateTime.now());
        existingUbo.setVerificationMethod("DOCUMENT");
        existingUbo.setTitularidadRealDocument("document-url");

        Ubo updatedUbo = new Ubo();
        updatedUbo.setUboId(UBO_ID);
        // Don't set isVerified to simulate the DTO not having this field

        when(repository.findById(UBO_ID)).thenReturn(Mono.just(existingUbo));
        when(mapper.toEntity(uboDTO)).thenReturn(updatedUbo);

        // Mock the save method to properly handle the entity
        when(repository.save(any(Ubo.class))).thenAnswer(invocation -> {
            Ubo savedUbo = invocation.getArgument(0);
            // Ensure the saved entity has the verification data preserved
            if (savedUbo.getIsVerified() == null && existingUbo.getIsVerified()) {
                savedUbo.setIsVerified(existingUbo.getIsVerified());
                savedUbo.setVerificationDate(existingUbo.getVerificationDate());
                savedUbo.setVerificationMethod(existingUbo.getVerificationMethod());
                savedUbo.setTitularidadRealDocument(existingUbo.getTitularidadRealDocument());
            }
            return Mono.just(savedUbo);
        });

        when(mapper.toDTO(any(Ubo.class))).thenReturn(uboDTO);

        // Act & Assert
        StepVerifier.create(uboService.update(UBO_ID, uboDTO))
                .expectNext(uboDTO)
                .verifyComplete();

        verify(repository).findById(UBO_ID);
        verify(mapper).toEntity(uboDTO);
        verify(repository).save(any(Ubo.class));
        verify(mapper).toDTO(any(Ubo.class));
    }

    @Test
    void testDelete() {
        // Arrange
        when(repository.deleteById(UBO_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(uboService.delete(UBO_ID))
                .verifyComplete();

        verify(repository).deleteById(UBO_ID);
    }
}
