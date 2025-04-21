package com.catalis.core.kycb.models.entities.location.v1;

import com.catalis.core.kycb.models.entities.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

/**
 * Entity representing a business location.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table("business_location")
public class BusinessLocation extends BaseEntity {

    @Id
    @Column("business_location_id")
    private Long businessLocationId;

    @Column("party_id")
    private Long partyId;

    @Column("location_type_code")
    private String locationTypeCode;

    @Column("is_primary")
    private Boolean isPrimary;

    @Column("address_line_1")
    private String addressLine1;

    @Column("address_line_2")
    private String addressLine2;

    @Column("postal_code")
    private String postalCode;

    @Column("city")
    private String city;

    @Column("division_code")
    private String divisionCode;

    @Column("country_iso_code")
    private String countryIsoCode;

    @Column("phone_number")
    private String phoneNumber;

    @Column("email")
    private String email;

    @Column("employee_count")
    private Integer employeeCount;

    @Column("activities_conducted")
    private String activitiesConducted;

    @Column("is_verified")
    private Boolean isVerified;

    @Column("verification_method")
    private String verificationMethod;

    @Column("verification_date")
    private LocalDateTime verificationDate;
}
