package com.firefly.core.kycb.models.repositories.corporate.v1;

import com.firefly.core.kycb.models.entities.corporate.v1.CorporateStructure;
import com.firefly.core.kycb.interfaces.enums.corporate.v1.RelationshipTypeEnum;
import com.firefly.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Repository for corporate structure operations.
 */
@Repository
public interface CorporateStructureRepository extends BaseRepository<CorporateStructure, UUID> {

    /**
     * Find corporate structures by party ID (subsidiary).
     *
     * @param partyId The ID of the party (subsidiary)
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByPartyId(UUID partyId);

    /**
     * Find corporate structures by parent entity ID.
     *
     * @param parentEntityId The ID of the parent entity
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByParentEntityId(UUID parentEntityId);

    /**
     * Find corporate structures by relationship type.
     *
     * @param relationshipTypeEnum The type of relationship
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByRelationshipType(RelationshipTypeEnum relationshipTypeEnum);

    /**
     * Find corporate structures by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByIsVerified(Boolean isVerified);

    /**
     * Find corporate structures with ownership percentage above a threshold.
     *
     * @param threshold The ownership percentage threshold
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByOwnershipPercentageGreaterThanEqual(BigDecimal threshold);

    /**
     * Find corporate structures by verification date range.
     *
     * @param startDate The start date
     * @param endDate The end date
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByVerificationDateBetween(LocalDateTime startDate, LocalDateTime endDate);

    /**
     * Find active corporate structures (not ended).
     *
     * @param currentDate The current date
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByEndDateAfterOrEndDateIsNull(LocalDateTime currentDate);

    /**
     * Find corporate structures by party ID and relationship type.
     *
     * @param partyId The ID of the party
     * @param relationshipTypeEnum The type of relationship
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByPartyIdAndRelationshipType(UUID partyId, RelationshipTypeEnum relationshipTypeEnum);

    /**
     * Find corporate structures by parent entity ID and relationship type.
     *
     * @param parentEntityId The ID of the parent entity
     * @param relationshipTypeEnum The type of relationship
     * @return A flux of corporate structures
     */
    Flux<CorporateStructure> findByParentEntityIdAndRelationshipType(UUID parentEntityId, RelationshipTypeEnum relationshipTypeEnum);
}
