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
