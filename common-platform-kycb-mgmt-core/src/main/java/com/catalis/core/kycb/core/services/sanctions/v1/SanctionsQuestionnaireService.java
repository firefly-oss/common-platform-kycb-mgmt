package com.catalis.core.kycb.core.services.sanctions.v1;

import com.catalis.core.kycb.interfaces.dtos.sanctions.v1.SanctionsQuestionnaireDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Service for managing sanctions questionnaires.
 */
public interface SanctionsQuestionnaireService {

    /**
     * Create a new sanctions questionnaire.
     *
     * @param sanctionsQuestionnaireDTO The sanctions questionnaire data
     * @return A mono of the created sanctions questionnaire
     */
    Mono<SanctionsQuestionnaireDTO> create(SanctionsQuestionnaireDTO sanctionsQuestionnaireDTO);

    /**
     * Find a sanctions questionnaire by ID.
     *
     * @param sanctionsQuestionnaireId The ID of the sanctions questionnaire
     * @return A mono of the found sanctions questionnaire
     */
    Mono<SanctionsQuestionnaireDTO> findById(Long sanctionsQuestionnaireId);

    /**
     * Find sanctions questionnaires by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of sanctions questionnaires
     */
    Flux<SanctionsQuestionnaireDTO> findByPartyId(Long partyId);

    /**
     * Find the latest sanctions questionnaire for a party.
     *
     * @param partyId The ID of the party
     * @return A mono of the latest sanctions questionnaire
     */
    Mono<SanctionsQuestionnaireDTO> findLatestByPartyId(Long partyId);

    /**
     * Update a sanctions questionnaire.
     *
     * @param sanctionsQuestionnaireId The ID of the sanctions questionnaire to update
     * @param sanctionsQuestionnaireDTO The updated sanctions questionnaire data
     * @return A mono of the updated sanctions questionnaire
     */
    Mono<SanctionsQuestionnaireDTO> update(Long sanctionsQuestionnaireId, SanctionsQuestionnaireDTO sanctionsQuestionnaireDTO);

    /**
     * Delete a sanctions questionnaire.
     *
     * @param sanctionsQuestionnaireId The ID of the sanctions questionnaire to delete
     * @return A mono of void
     */
    Mono<Void> delete(Long sanctionsQuestionnaireId);
}