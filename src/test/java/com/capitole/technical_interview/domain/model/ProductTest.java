package com.capitole.technical_interview.domain.model;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {
    @Test
    void shouldThrowExceptionForInvalidSku() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Product("SK1", new BigDecimal("19.99"), "Wireless Mouse", "Electronics"));
        assertEquals("SKU must be exactly 7 characters", exception.getMessage());
    }

    @Test
    void shouldThrowExceptionForNegativeOrZeroPrice() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
                new Product("SKU0001", new BigDecimal("-5.00"), "Wireless Mouse", "Electronics"));
        assertEquals("Price must be greater than 0", exception.getMessage());
    }
}