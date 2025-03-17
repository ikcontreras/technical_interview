package com.capitole.technical_interview.domain.discount;

import com.capitole.technical_interview.domain.model.Product;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class SkuDiscountRule implements DiscountRule {

    private static final BigDecimal DISCOUNT = new BigDecimal("0.30");

    @Override
    public void applyDiscount(Product product) {
        if (product.getSku().endsWith("5")) {
            BigDecimal discountAmount = product.getPrice().multiply(DISCOUNT);
            product.setPrice(product.getPrice().subtract(discountAmount));
        }
    }
}
