package com.catalis.core.kycb.core.services.sanctions.v1;

import com.catalis.core.kycb.core.mappers.sanctions.v1.SanctionsQuestionnaireMapper;
import com.catalis.core.kycb.interfaces.dtos.sanctions.v1.SanctionsQuestionnaireDTO;
import com.catalis.core.kycb.models.entities.sanctions.v1.SanctionsQuestionnaire;
import com.catalis.core.kycb.models.repositories.sanctions.v1.SanctionsQuestionnaireRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the SanctionsQuestionnaireService.
 */
@Slf4j
@Service
@Transactional
public class SanctionsQuestionnaireServiceImpl implements SanctionsQuestionnaireService {

    @Autowired
    private SanctionsQuestionnaireRepository sanctionsQuestionnaireRepository;

    @Autowired
    private SanctionsQuestionnaireMapper mapper;

    @Override
    public Mono<SanctionsQuestionnaireDTO> create(SanctionsQuestionnaireDTO sanctionsQuestionnaireDTO) {
        log.debug("Creating sanctions questionnaire for party ID: {}", sanctionsQuestionnaireDTO.getPartyId());

        SanctionsQuestionnaire sanctionsQuestionnaire = mapper.toEntity(sanctionsQuestionnaireDTO);
        sanctionsQuestionnaire.setQuestionnaireDate(LocalDateTime.now());

        return sanctionsQuestionnaireRepository.save(sanctionsQuestionnaire)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SanctionsQuestionnaireDTO> findById(Long sanctionsQuestionnaireId) {
        log.debug("Finding sanctions questionnaire by ID: {}", sanctionsQuestionnaireId);

        return sanctionsQuestionnaireRepository.findById(sanctionsQuestionnaireId)
                .map(mapper::toDTO);
    }

    @Override
    public Flux<SanctionsQuestionnaireDTO> findByPartyId(Long partyId) {
        log.debug("Finding sanctions questionnaires by party ID: {}", partyId);

        return sanctionsQuestionnaireRepository.findByPartyId(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SanctionsQuestionnaireDTO> findLatestByPartyId(Long partyId) {
        log.debug("Finding latest sanctions questionnaire by party ID: {}", partyId);

        return sanctionsQuestionnaireRepository.findFirstByPartyIdOrderByQuestionnaireDateDesc(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<SanctionsQuestionnaireDTO> update(Long sanctionsQuestionnaireId, SanctionsQuestionnaireDTO sanctionsQuestionnaireDTO) {
        log.debug("Updating sanctions questionnaire with ID: {}", sanctionsQuestionnaireId);

        return sanctionsQuestionnaireRepository.findById(sanctionsQuestionnaireId)
                .flatMap(existingQuestionnaire -> {
                    SanctionsQuestionnaire updatedEntity = mapper.toEntity(sanctionsQuestionnaireDTO);
                    updatedEntity.setSanctionsQuestionnaireId(sanctionsQuestionnaireId);
                    updatedEntity.setQuestionnaireDate(LocalDateTime.now());
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingQuestionnaire.getDateCreated());

                    return sanctionsQuestionnaireRepository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long sanctionsQuestionnaireId) {
        log.debug("Deleting sanctions questionnaire with ID: {}", sanctionsQuestionnaireId);

        return sanctionsQuestionnaireRepository.deleteById(sanctionsQuestionnaireId);
    }
}
