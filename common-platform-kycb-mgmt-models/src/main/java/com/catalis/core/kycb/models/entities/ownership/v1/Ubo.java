package com.catalis.core.kycb.models.entities.ownership.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import com.catalis.core.kycb.interfaces.enums.ownership.v1.OwnershipTypeEnum;
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
 * Entity representing an Ultimate Beneficial Owner.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("ubo")
public class Ubo extends BaseEntity {

    @Id
    @Column("ubo_id")
    private Long uboId;

    @Column("party_id")
    private Long partyId;

    @Column("natural_person_id")
    private Long naturalPersonId;

    @Column("ownership_percentage")
    private BigDecimal ownershipPercentage;

    @Column("ownership_type")
    private OwnershipTypeEnum ownershipType;

    @Column("control_structure")
    private String controlStructure;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verification_method")
    private String verificationMethod;

    @Column("titularidad_real_document")
    private String titularidadRealDocument;

    @Column("verification_date")
    private LocalDateTime verificationDate;

    @Column("start_date")
    private LocalDateTime startDate;

    @Column("end_date")
    private LocalDateTime endDate;
}
