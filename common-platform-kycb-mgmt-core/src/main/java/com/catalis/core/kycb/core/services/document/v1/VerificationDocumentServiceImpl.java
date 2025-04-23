package com.catalis.core.kycb.core.services.document.v1;

import com.catalis.common.core.filters.FilterRequest;
import com.catalis.common.core.filters.FilterUtils;
import com.catalis.common.core.queries.PaginationResponse;
import com.catalis.core.kycb.core.mappers.document.v1.VerificationDocumentMapper;
import com.catalis.core.kycb.interfaces.dtos.document.v1.VerificationDocumentDTO;
import com.catalis.core.kycb.models.entities.document.v1.VerificationDocument;
import com.catalis.core.kycb.models.repositories.document.v1.VerificationDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Service
@Transactional
public class VerificationDocumentServiceImpl implements VerificationDocumentService {

    @Autowired
    private VerificationDocumentRepository repository;

    @Autowired
    private VerificationDocumentMapper mapper;

    @Override
    public Mono<PaginationResponse<VerificationDocumentDTO>> findAll(FilterRequest<VerificationDocumentDTO> filterRequest) {
        return FilterUtils.createFilter(
                VerificationDocument.class,
                mapper::toDTO
        ).filter(filterRequest);
    }

    @Override
    public Mono<VerificationDocumentDTO> create(VerificationDocumentDTO dto) {
        VerificationDocument entity = mapper.toEntity(dto);
        return repository.save(entity)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VerificationDocumentDTO> getById(Long verificationDocumentId) {
        return repository.findById(verificationDocumentId)
                .map(mapper::toDTO);
    }

    @Override
    public Mono<VerificationDocumentDTO> update(Long verificationDocumentId, VerificationDocumentDTO dto) {
        return repository.findById(verificationDocumentId)
                .flatMap(existingEntity -> {
                    VerificationDocument updatedEntity = mapper.toEntity(dto);
                    updatedEntity.setVerificationDocumentId(verificationDocumentId);
                    // Preserve creation date
                    updatedEntity.setDateCreated(existingEntity.getDateCreated());
                    return repository.save(updatedEntity);
                })
                .map(mapper::toDTO);
    }

    @Override
    public Mono<Void> delete(Long verificationDocumentId) {
        return repository.deleteById(verificationDocumentId);
    }
}
