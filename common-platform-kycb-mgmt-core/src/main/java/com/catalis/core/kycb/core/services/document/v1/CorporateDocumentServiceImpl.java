package com.catalis.core.kycb.core.services.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.document.v1.CorporateDocumentMapper;
import com.catalis.core.kycb.interfaces.dtos.document.v1.CorporateDocumentDTO;
import com.catalis.core.kycb.interfaces.enums.document.v1.CorporateDocumentTypeEnum;
import com.catalis.core.kycb.interfaces.enums.document.v1.DocumentTypeEnum;
import com.catalis.core.kycb.models.entities.document.v1.CorporateDocument;
import com.catalis.core.kycb.models.repositories.document.v1.CorporateDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

/**
 * Implementation of the corporate document service.
 */
@Service
@Transactional
public class CorporateDocumentServiceImpl implements CorporateDocumentService {

    @Autowired
    private CorporateDocumentRepository repository;

    @Autowired
    private CorporateDocumentMapper mapper;

    @Override
    public Mono<PaginationResponse<CorporateDocumentDTO>> findAll(FilterRequest<CorporateDocumentDTO> filterRequest) {
        return FilterUtils.createFilter(
                CorporateDocument.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Flux<CorporateDocumentDTO> findByPartyId(Long partyId) {
        return repository.findByPartyId(partyId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateDocumentDTO> create(CorporateDocumentDTO dto) {
        CorporateDocument entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateDocumentDTO> getById(Long documentId) {
        return repository.findById(documentId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateDocumentDTO> update(Long documentId, CorporateDocumentDTO dto) {
        return repository.findById(documentId)
                .flatMap(existingEntity -> {
                    CorporateDocument updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setCorporateDocumentId(documentId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    // Preserve verification data if not changing
                    if (existingEntity.getIsVerified() && updatedEntity.getIsVerified() == null) {
                        updatedEntity.setIsVerified(existingEntity.getIsVerified());
                        updatedEntity.setVerificationDate(existingEntity.getVerificationDate());
                        updatedEntity.setVerificationNotes(existingEntity.getVerificationNotes());
                        updatedEntity.setVerificationAgent(existingEntity.getVerificationAgent());
                    }
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long documentId) {
        return repository.deleteById(documentId);
    }

    @Override
    public Flux<CorporateDocumentDTO> findByDocumentType(String documentType) {
        // Convert CorporateDocumentTypeEnum to DocumentTypeEnum
        DocumentTypeEnum docTypeEnum;
        try {
            CorporateDocumentTypeEnum corpDocType = CorporateDocumentTypeEnum.valueOf(documentType);
            // Map CorporateDocumentTypeEnum to DocumentTypeEnum
            switch (corpDocType) {
                case DEED_OF_INCORPORATION:
                    docTypeEnum = DocumentTypeEnum.DEED_OF_INCORPORATION;
                    break;
                case BYLAWS:
                    docTypeEnum = DocumentTypeEnum.BYLAW;
                    break;
                case POWER_OF_ATTORNEY:
                    docTypeEnum = DocumentTypeEnum.POWER_OF_ATTORNEY;
                    break;
                case BOARD_RESOLUTION:
                    docTypeEnum = DocumentTypeEnum.BOARD_RESOLUTION;
                    break;
                case TAX_ID:
                    docTypeEnum = DocumentTypeEnum.TAX_ID;
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported document type: " + documentType);
            }
        } catch (IllegalArgumentException e) {
            // If the input is not a valid CorporateDocumentTypeEnum, try to parse it as DocumentTypeEnum directly
            docTypeEnum = DocumentTypeEnum.valueOf(documentType);
        }

        return repository.findByDocumentType(docTypeEnum)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateDocumentDTO> verifyDocument(Long documentId, String verificationNotes) {
        return repository.findById(documentId)
                .flatMap(entity -> {
                    entity.setIsVerified(true);
                    entity.setVerificationDate(LocalDateTime.now());
                    entity.setVerificationNotes(verificationNotes);
                    // In a real implementation, we would set the verification agent to the current user
                    entity.setVerificationAgent("System");
                    return repository.save(entity);
                })
                .map(mapper::toDTO);
    }
}
