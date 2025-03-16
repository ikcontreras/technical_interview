package com.capitole.technical_interview.domain.discount;

import com.capitole.technical_interview.domain.model.Product;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class SkuDiscountRuleTest {
    private final SkuDiscountRule discountRule = new SkuDiscountRule();

    @Test
    void shouldApply30PercentDiscountForSkuEndingIn5() {
        Product product = new Product("SKU0005", new BigDecimal("120.00"), "Noise-Cancelling Headphones", "Electronics");
        discountRule.applyDiscount(product);
        assertEquals(new BigDecimal("84.0000"), product.getPrice());
    }

    @Test
    void shouldNotApplyDiscountForOtherSkus() {
        Product product = new Product("SKU0002", new BigDecimal("500.00"), "4K Smart TV", "Electronics");
        discountRule.applyDiscount(product);
        assertEquals(new BigDecimal("500.00"), product.getPrice());
    }
}