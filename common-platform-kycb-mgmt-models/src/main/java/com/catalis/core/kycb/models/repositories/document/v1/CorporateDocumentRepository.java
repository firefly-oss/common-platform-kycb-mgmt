package com.catalis.core.kycb.models.repositories.document.v1;

import com.catalis.core.kycb.models.entities.document.v1.CorporateDocument;
import com.catalis.core.kycb.interfaces.enums.document.v1.DocumentTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

/**
 * Repository for corporate document operations.
 */
@Repository
public interface CorporateDocumentRepository extends BaseRepository<CorporateDocument, Long> {

    /**
     * Find corporate documents by party ID.
     *
     * @param partyId The ID of the party
     * @return A flux of corporate documents
     */
    Flux<CorporateDocument> findByPartyId(Long partyId);

    /**
     * Find corporate documents by document type.
     *
     * @param documentTypeEnum The type of document
     * @return A flux of corporate documents
     */
    Flux<CorporateDocument> findByDocumentType(DocumentTypeEnum documentTypeEnum);

    /**
     * Find corporate documents by party ID and document type.
     *
     * @param partyId The ID of the party
     * @param documentTypeEnum The type of document
     * @return A flux of corporate documents
     */
    Flux<CorporateDocument> findByPartyIdAndDocumentType(Long partyId, DocumentTypeEnum documentTypeEnum);

    /**
     * Find corporate documents by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of corporate documents
     */
    Flux<CorporateDocument> findByIsVerified(Boolean isVerified);

    /**
     * Find corporate documents that are about to expire.
     *
     * @param expiryDate The expiry date threshold
     * @return A flux of corporate documents
     */
    Flux<CorporateDocument> findByExpiryDateBefore(LocalDateTime expiryDate);
}
