package com.catalis.core.kycb.models.repositories.sanctions.v1;

import com.catalis.core.kycb.models.entities.sanctions.v1.SanctionsQuestionnaire;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Repository for sanctions questionnaire operations.
 */
@Repository
public interface SanctionsQuestionnaireRepository extends BaseRepository<SanctionsQuestionnaire, Long> {

    /**
     * Find sanctions questionnaires by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of sanctions questionnaires
     */
    Flux<SanctionsQuestionnaire> findByPartyId(Long partyId);

    /**
     * Find the latest sanctions questionnaire for a party.
     *
     * @param partyId The ID of the party
     * @return A mono of the latest sanctions questionnaire
     */
    Mono<SanctionsQuestionnaire> findFirstByPartyIdOrderByQuestionnaireDateDesc(Long partyId);
}