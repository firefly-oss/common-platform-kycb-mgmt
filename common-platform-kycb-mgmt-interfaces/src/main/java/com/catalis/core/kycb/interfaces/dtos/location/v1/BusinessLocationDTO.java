package com.catalis.core.kycb.interfaces.dtos.location.v1;

import com.catalis.annotations.ValidDateTime;
import com.catalis.annotations.ValidPhoneNumber;
import com.catalis.core.kycb.interfaces.dtos.BaseDTO;
import com.catalis.core.utils.annotations.FilterableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * DTO for business location data.
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BusinessLocationDTO extends BaseDTO {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long businessLocationId;

    @FilterableId
    private Long partyId;

    private String locationTypeCode;
    private Boolean isPrimary;
    private String addressLine1;
    private String addressLine2;
    private String postalCode;
    private String city;
    private String divisionCode;
    private String countryIsoCode;
    @ValidPhoneNumber
    private String phoneNumber;
    @Email
    private String email;
    private Integer employeeCount;
    private String activitiesConducted;
    private Boolean isVerified;
    private String verificationMethod;
    @ValidDateTime
    private LocalDateTime verificationDate;
}
