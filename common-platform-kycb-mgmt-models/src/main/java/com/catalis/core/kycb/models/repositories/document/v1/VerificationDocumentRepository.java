package com.catalis.core.kycb.models.repositories.document.v1;

import com.catalis.core.kycb.models.entities.document.v1.VerificationDocument;
import com.catalis.core.kycb.interfaces.enums.document.v1.DocumentTypeEnum;
import com.catalis.core.kycb.models.repositories.BaseRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

/**
 * Repository for verification document operations.
 */
@Repository
public interface VerificationDocumentRepository extends BaseRepository<VerificationDocument, Long> {

    /**
     * Find verification documents by KYC verification ID.
     *
     * @param kycVerificationId The ID of the KYC verification
     * @return A flux of verification documents
     */
    Flux<VerificationDocument> findByKycVerificationId(Long kycVerificationId);

    /**
     * Find verification documents by document type.
     *
     * @param documentTypeEnum The type of document
     * @return A flux of verification documents
     */
    Flux<VerificationDocument> findByDocumentType(DocumentTypeEnum documentTypeEnum);

    /**
     * Find verification documents by KYC verification ID and document type.
     *
     * @param kycVerificationId The ID of the KYC verification
     * @param documentTypeEnum The type of document
     * @return A flux of verification documents
     */
    Flux<VerificationDocument> findByKycVerificationIdAndDocumentType(Long kycVerificationId, DocumentTypeEnum documentTypeEnum);

    /**
     * Find verification documents by verification status.
     *
     * @param isVerified The verification status
     * @return A flux of verification documents
     */
    Flux<VerificationDocument> findByIsVerified(Boolean isVerified);
}