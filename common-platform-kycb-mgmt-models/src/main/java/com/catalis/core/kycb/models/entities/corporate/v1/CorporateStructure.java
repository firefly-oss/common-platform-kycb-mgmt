package com.catalis.core.kycb.models.entities.corporate.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.corporate.v1.RelationshipTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity representing a corporate structure relationship.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("corporate_structure")
public class CorporateStructure extends BaseEntity {

    @Id
    @Column("corporate_structure_id")
    private Long corporateStructureId;

    @Column("party_id")
    private Long partyId;

    @Column("parent_entity_id")
    private Long parentEntityId;

    @Column("ownership_percentage")
    private BigDecimal ownershipPercentage;

    @Column("relationship_type")
    private RelationshipTypeEnum relationshipType;

    @Column("control_notes")
    private String controlNotes;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verification_date")
    private LocalDateTime verificationDate;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;
}
