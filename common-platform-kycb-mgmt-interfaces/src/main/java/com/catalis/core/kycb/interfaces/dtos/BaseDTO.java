package com.catalis.core.kycb.interfaces.dtos;

import com.catalis.annotations.ValidDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseDTO {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ValidDateTime
    private LocalDateTime dateCreated;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ValidDateTime
    private LocalDateTime dateUpdated;
}
