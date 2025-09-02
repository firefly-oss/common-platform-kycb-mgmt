package com.firefly.core.kycb.models.entities.economic.v1;

import com.firefly.core.kycb.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import java.util.UUID;

/**
 * Entity representing an economic activity of a party.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("economic_activity")
public class EconomicActivity extends BaseEntity {

    @Id
    @Column("economic_activity_id")
    private UUID economicActivityId;

    @Column("party_id")
    private UUID partyId;

    @Column("activity_code")
    private String activityCode;

    @Column("is_primary")
    private Boolean isPrimary;

    @Column("sector_code")
    private String sectorCode;

    @Column("subsector")
    private String subsector;

    @Column("high_risk_activity")
    private Boolean highRiskActivity;

    @Column("activity_details")
    private String activityDetails;

    @Column("geographic_scope_code")
    private String geographicScopeCode;

    @Column("export_markets")
    private String exportMarkets;

    @Column("import_markets")
    private String importMarkets;

    @Column("regulated_activity")
    private Boolean regulatedActivity;

    @Column("regulatory_details")
    private String regulatoryDetails;
}
