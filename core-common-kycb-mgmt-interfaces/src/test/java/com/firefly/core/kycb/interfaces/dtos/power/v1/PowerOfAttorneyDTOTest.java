/*
 * Copyright 2025 Firefly Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.firefly.core.kycb.interfaces.dtos.power.v1;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class to verify PowerOfAttorneyDTO validation, especially the POA completion field.
 */
class PowerOfAttorneyDTOTest {

    private Validator validator;

    @BeforeEach
    void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void testPoaCompletedValidationAnnotation() {
        // Test that the POA completed field has the correct validation annotation
        PowerOfAttorneyDTO dto = new PowerOfAttorneyDTO();

        // Use reflection to check the annotation is present
        try {
            var field = PowerOfAttorneyDTO.class.getDeclaredField("isPoaCompleted");
            var notNullAnnotation = field.getAnnotation(jakarta.validation.constraints.NotNull.class);

            assertNotNull(notNullAnnotation, "isPoaCompleted field should have @NotNull annotation");
            assertEquals("POA completed flag is required", notNullAnnotation.message(),
                "Validation message should reference POA instead of bastanteo");
        } catch (NoSuchFieldException e) {
            fail("isPoaCompleted field should exist");
        }
    }

    @Test
    void testFieldRenamed() {
        // Test that the old bastanteo field no longer exists and new POA field exists
        PowerOfAttorneyDTO dto = new PowerOfAttorneyDTO();

        // Check that old field doesn't exist
        try {
            PowerOfAttorneyDTO.class.getDeclaredField("isBastanteoCompleted");
            fail("isBastanteoCompleted field should no longer exist");
        } catch (NoSuchFieldException e) {
            // Expected - field should not exist
        }

        // Check that new field exists
        try {
            var field = PowerOfAttorneyDTO.class.getDeclaredField("isPoaCompleted");
            assertNotNull(field, "isPoaCompleted field should exist");
        } catch (NoSuchFieldException e) {
            fail("isPoaCompleted field should exist");
        }
    }

    @Test
    void testPoaCompletedFieldAccess() {
        // Test that the field can be set and retrieved correctly
        PowerOfAttorneyDTO dto = new PowerOfAttorneyDTO();
        
        // Test setting to true
        dto.setIsPoaCompleted(true);
        assertTrue(dto.getIsPoaCompleted());
        
        // Test setting to false
        dto.setIsPoaCompleted(false);
        assertFalse(dto.getIsPoaCompleted());
        
        // Test setting to null
        dto.setIsPoaCompleted(null);
        assertNull(dto.getIsPoaCompleted());
    }
}
