package com.capitole.technical_interview.domain.discount;

import com.capitole.technical_interview.domain.model.Product;

public interface DiscountRule {
    void applyDiscount(Product product);
}
