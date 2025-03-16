package com.capitole.technical_interview.domain.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Getter @Setter @NoArgsConstructor
public class Product {
    private String sku;
    private BigDecimal price;
    private String description;
    private String category;

    public Product(String sku, BigDecimal price, String description, String category) {
        if (sku == null || sku.length() != 7) {
            throw new IllegalArgumentException("SKU must be exactly 7 characters");
        }
        if (price == null || price.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        this.sku = sku;
        this.price = price;
        this.description = description;
        this.category = category;
    }
}
