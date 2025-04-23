package com.catalis.core.kycb.core.services.sanctions.v1;

import com.catalis.core.kycb.core.mappers.sanctions.v1.SanctionsQuestionnaireMapper;
import com.catalis.core.kycb.interfaces.dtos.sanctions.v1.SanctionsQuestionnaireDTO;
import com.catalis.core.kycb.interfaces.enums.sanctions.v1.EntitySanctionsQuestionnaireTypeEnum;
import com.catalis.core.kycb.models.entities.sanctions.v1.SanctionsQuestionnaire;
import com.catalis.core.kycb.models.repositories.sanctions.v1.SanctionsQuestionnaireRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SanctionsQuestionnaireServiceImplTest {

    @Mock
    private SanctionsQuestionnaireRepository repository;

    @Mock
    private SanctionsQuestionnaireMapper mapper;

    @InjectMocks
    private SanctionsQuestionnaireServiceImpl service;

    private SanctionsQuestionnaire entity;
    private SanctionsQuestionnaireDTO dto;
    private final Long PARTY_ID = 1L;
    private final Long QUESTIONNAIRE_ID = 1L;

    @BeforeEach
    void setUp() {
        // Setup test data
        entity = SanctionsQuestionnaire.builder()
                .sanctionsQuestionnaireId(QUESTIONNAIRE_ID)
                .partyId(PARTY_ID)
                .entitySanctionsQuestionnaire(EntitySanctionsQuestionnaireTypeEnum.LEGAL_ENTITY_ONLY)
                .activityOutsideEu(true)
                .economicSanctions(false)
                .residentCountriesSanctions(false)
                .involvedSanctions(false)
                .questionnaireDate(LocalDateTime.now())
                .build();

        dto = SanctionsQuestionnaireDTO.builder()
                .sanctionsQuestionnaireId(QUESTIONNAIRE_ID)
                .partyId(PARTY_ID)
                .entitySanctionsQuestionnaire(EntitySanctionsQuestionnaireTypeEnum.LEGAL_ENTITY_ONLY)
                .activityOutsideEu(true)
                .economicSanctions(false)
                .residentCountriesSanctions(false)
                .involvedSanctions(false)
                .questionnaireDate(LocalDateTime.now())
                .build();
    }

    @Test
    void create_ShouldCreateSanctionsQuestionnaire() {
        // Arrange
        when(mapper.toEntity(any(SanctionsQuestionnaireDTO.class))).thenReturn(entity);
        when(repository.save(any(SanctionsQuestionnaire.class))).thenReturn(Mono.just(entity));
        when(mapper.toDTO(any(SanctionsQuestionnaire.class))).thenReturn(dto);

        // Act & Assert
        StepVerifier.create(service.create(dto))
                .expectNext(dto)
                .verifyComplete();

        verify(mapper).toEntity(any(SanctionsQuestionnaireDTO.class));
        verify(repository).save(any(SanctionsQuestionnaire.class));
        verify(mapper).toDTO(any(SanctionsQuestionnaire.class));
    }

    @Test
    void findById_ShouldReturnSanctionsQuestionnaire() {
        // Arrange
        when(repository.findById(QUESTIONNAIRE_ID)).thenReturn(Mono.just(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        // Act & Assert
        StepVerifier.create(service.findById(QUESTIONNAIRE_ID))
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(QUESTIONNAIRE_ID);
        verify(mapper).toDTO(entity);
    }

    @Test
    void findByPartyId_ShouldReturnSanctionsQuestionnaires() {
        // Arrange
        when(repository.findByPartyId(PARTY_ID)).thenReturn(Flux.just(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        // Act & Assert
        StepVerifier.create(service.findByPartyId(PARTY_ID))
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findByPartyId(PARTY_ID);
        verify(mapper).toDTO(entity);
    }

    @Test
    void findLatestByPartyId_ShouldReturnLatestSanctionsQuestionnaire() {
        // Arrange
        when(repository.findFirstByPartyIdOrderByQuestionnaireDateDesc(PARTY_ID)).thenReturn(Mono.just(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        // Act & Assert
        StepVerifier.create(service.findLatestByPartyId(PARTY_ID))
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findFirstByPartyIdOrderByQuestionnaireDateDesc(PARTY_ID);
        verify(mapper).toDTO(entity);
    }

    @Test
    void update_ShouldUpdateSanctionsQuestionnaire() {
        // Arrange
        when(repository.findById(QUESTIONNAIRE_ID)).thenReturn(Mono.just(entity));
        when(mapper.toEntity(dto)).thenReturn(entity);
        when(repository.save(entity)).thenReturn(Mono.just(entity));
        when(mapper.toDTO(entity)).thenReturn(dto);

        // Act & Assert
        StepVerifier.create(service.update(QUESTIONNAIRE_ID, dto))
                .expectNext(dto)
                .verifyComplete();

        verify(repository).findById(QUESTIONNAIRE_ID);
        verify(mapper).toEntity(dto);
        verify(repository).save(entity);
        verify(mapper).toDTO(entity);
    }

    @Test
    void delete_ShouldDeleteSanctionsQuestionnaire() {
        // Arrange
        when(repository.deleteById(QUESTIONNAIRE_ID)).thenReturn(Mono.empty());

        // Act & Assert
        StepVerifier.create(service.delete(QUESTIONNAIRE_ID))
                .verifyComplete();

        verify(repository).deleteById(QUESTIONNAIRE_ID);
    }
}
