package com.capitole.technical_interview.domain.discount;

import com.capitole.technical_interview.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ElectronicsDiscountRuleTest {
    private final ElectronicsDiscountRule discountRule = new ElectronicsDiscountRule();

    @Test
    void shouldApply15PercentDiscountForElectronics() {
        Product product = new Product("SKU0002", new BigDecimal("500.00"), "4K Smart TV", "Electronics");
        discountRule.applyDiscount(product);
        assertEquals(new BigDecimal("425.0000"), product.getPrice());
    }

    @Test
    void shouldNotApplyDiscountForOtherCategories() {
        Product product = new Product("SKU0003", new BigDecimal("100.00"), "Water Bottle", "Home & Kitchen");
        discountRule.applyDiscount(product);
        assertEquals(new BigDecimal("100.00"), product.getPrice());
    }
}