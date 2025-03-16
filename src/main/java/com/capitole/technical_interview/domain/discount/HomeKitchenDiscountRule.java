package com.capitole.technical_interview.domain.discount;

import com.capitole.technical_interview.domain.model.Product;

import java.math.BigDecimal;

public class HomeKitchenDiscountRule implements DiscountRule {
    private static final String CATEGORY = "Home & Kitchen";
    private static final BigDecimal DISCOUNT = new BigDecimal("0.25"); // 25% de descuento

    @Override
    public void applyDiscount(Product product) {
        if (CATEGORY.equals(product.getCategory())) {
            BigDecimal discountAmount = product.getPrice().multiply(DISCOUNT);
            product.setPrice(product.getPrice().subtract(discountAmount));
        }
    }
}
