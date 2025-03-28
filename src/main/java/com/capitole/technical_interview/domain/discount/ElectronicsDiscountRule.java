package com.capitole.technical_interview.domain.discount;

import com.capitole.technical_interview.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class ElectronicsDiscountRule implements DiscountRule {
    private static final String CATEGORY = "Electronics";
    private static final BigDecimal DISCOUNT = new BigDecimal("0.15");

    @Override
    public void applyDiscount(Product product) {
        if (CATEGORY.equals(product.getCategory())) {
            BigDecimal discountAmount = product.getPrice().multiply(DISCOUNT);
            product.setPrice(product.getPrice().subtract(discountAmount));
        }
    }
}
