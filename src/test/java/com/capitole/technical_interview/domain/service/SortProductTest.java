package com.capitole.technical_interview.domain.service;

import com.capitole.technical_interview.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SortProductTest {
    private SortProducts sortProducts;

    @BeforeEach
    void setUp() {
        sortProducts = new SortProducts();
    }

    @Test
    void shouldSortProductsBySkuAscending() {
        List<Product> products = Arrays.asList(
                new Product("SKU0002", new BigDecimal("100.00"), "TV", "Electronics"),
                new Product("SKU0001", new BigDecimal("200.00"), "Laptop", "Electronics")
        );

        List<Product> sortedProducts = sortProducts.sort(products, "sku", true);

        assertEquals("SKU0001", sortedProducts.get(0).getSku());
        assertEquals("SKU0002", sortedProducts.get(1).getSku());
    }

    @Test
    void shouldSortProductsByPriceDescending() {
        List<Product> products = Arrays.asList(
                new Product("SKU0002", new BigDecimal("100.00"), "TV", "Electronics"),
                new Product("SKU0001", new BigDecimal("200.00"), "Laptop", "Electronics")
        );

        List<Product> sortedProducts = sortProducts.sort(products, "price", false);

        assertEquals(new BigDecimal("200.00"), sortedProducts.get(0).getPrice());
        assertEquals(new BigDecimal("100.00"), sortedProducts.get(1).getPrice());
    }

    @Test
    void shouldSortProductsByCategoryAscending() {
        List<Product> products = Arrays.asList(
                new Product("SKU0002", new BigDecimal("100.00"), "TV", "Electronics"),
                new Product("SKU0001", new BigDecimal("200.00"), "Laptop", "Home & Kitchen")
        );

        List<Product> sortedProducts = sortProducts.sort(products, "category", true);

        assertEquals("Electronics", sortedProducts.get(0).getCategory());
        assertEquals("Home & Kitchen", sortedProducts.get(1).getCategory());
    }

    @Test
    void shouldThrowExceptionForInvalidSortField() {
        List<Product> products = Arrays.asList(
                new Product("SKU0002", new BigDecimal("100.00"), "TV", "Electronics"),
                new Product("SKU0001", new BigDecimal("200.00"), "Laptop", "Home & Kitchen")
        );

        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                sortProducts.sort(products, "invalidField", true));

        assertEquals("Invalid sort field: invalidField", exception.getMessage());
    }
}