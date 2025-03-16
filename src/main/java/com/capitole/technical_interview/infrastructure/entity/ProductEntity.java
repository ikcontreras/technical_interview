package com.capitole.technical_interview.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "products")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ProductEntity {
    @Id
    @Column(name = "sku", nullable = false, unique = true)
    private String sku;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "category", nullable = false)
    private String category;

}
