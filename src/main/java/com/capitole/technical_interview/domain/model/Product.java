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
}
