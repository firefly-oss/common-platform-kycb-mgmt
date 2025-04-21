package com.catalis.core.kycb.models.entities.business.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigDecimal;

/**
 * Entity representing a business profile snapshot.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("business_profile")
public class BusinessProfile extends BaseEntity {

    @Id
    @Column("business_profile_id")
    private Long businessProfileId;

    @Column("party_id")
    private Long partyId;

    @Column("legal_form_code")
    private String legalFormCode;

    @Column("business_description")
    private String businessDescription;

    @Column("website_url")
    private String websiteUrl;

    @Column("incorporation_year")
    private Integer incorporationYear;

    @Column("employee_count")
    private Integer employeeCount;

    @Column("employee_range_code")
    private String employeeRangeCode;

    @Column("annual_revenue")
    private BigDecimal annualRevenue;

    @Column("currency_iso_code")
    private String currencyIsoCode;

    @Column("revenue_range_code")
    private String revenueRangeCode;

    @Column("stock_exchange")
    private String stockExchange;

    @Column("stock_symbol")
    private String stockSymbol;

    @Column("is_regulated")
    private Boolean isRegulated;

    @Column("regulatory_authority")
    private String regulatoryAuthority;

    @Column("company_status_code")
    private String companyStatusCode;

    @Column("company_size_code")
    private String companySizeCode;

    @Column("is_public_entity")
    private Boolean isPublicEntity;

    @Column("registration_number")
    private String registrationNumber;
}
