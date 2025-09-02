package com.firefly.core.kycb.core.services.document.v1;

import com.firefly.common.core.filters.FilterRequest;
import com.firefly.common.core.filters.FilterUtils;
import com.firefly.common.core.queries.PaginationResponse;
import com.firefly.core.kycb.core.mappers.document.v1.CorporateDocumentMapper;
import com.firefly.core.kycb.interfaces.dtos.document.v1.CorporateDocumentDTO;
import com.firefly.core.kycb.models.entities.document.v1.CorporateDocument;
import com.firefly.core.kycb.models.repositories.document.v1.CorporateDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import java.util.UUID;

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
    public Mono<CorporateDocumentDTO> create(CorporateDocumentDTO dto) {
        CorporateDocument entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateDocumentDTO> getById(UUID documentId) {
        return repository.findById(documentId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<CorporateDocumentDTO> update(UUID documentId, CorporateDocumentDTO dto) {
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
    public Mono<Void> delete(UUID documentId) {
        return repository.deleteById(documentId);
    }
}
