package com.catalis.core.kycb.models.entities.document.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.document.v1.CorporateDocumentTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a corporate document.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("corporate_document")
public class CorporateDocument extends BaseEntity {

    @Id
    @Column("corporate_document_id")
    private Long corporateDocumentId;

    @Column("party_id")
    private Long partyId;

    @Column("document_type")
    private CorporateDocumentTypeEnum documentType;

    @Column("document_reference")
    private String documentReference;

    @Column("document_system_id")
    private String documentSystemId;

    @Column("notary_name")
    private String notaryName;

    @Column("notary_division_code")
    private String notaryDivisionCode;

    @Column("commercial_registry")
    private String commercialRegistry;

    @Column("registry_entry")
    private String registryEntry;

    @Column("issue_date")
    private LocalDateTime issueDate;

    @Column("registry_date")
    private LocalDateTime registryDate;

    @Column("expiry_date")
    private LocalDateTime expiryDate;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verification_notes")
    private String verificationNotes;

    @Column("verification_date")
    private LocalDateTime verificationDate;

    @Column("verification_agent")
    private String verificationAgent;
}
