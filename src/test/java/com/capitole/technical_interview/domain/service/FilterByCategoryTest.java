package com.capitole.technical_interview.domain.service;

import com.capitole.technical_interview.domain.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterByCategoryTest {
    private Filters filters;

    @BeforeEach
    void setUp() {
        filters = new Filters();
    }

    @Test
    void shouldApplyDiscountsToFilteredProducts() {
        List<Product> products = Arrays.asList(
            new Product("SKU0001", new BigDecimal("19.99"), "Wireless Mouse", "Electronics"),
            new Product("SKU0002", new BigDecimal("499.00"), "4K Smart TV", "Electronics"),
            new Product("SKU0003", new BigDecimal("29.50"), "Water Bottle", "Home & Kitchen")
        );

        List<Product> filteredProducts = filters.filterByCategory(products, "Electronics");

        assertEquals(2, filteredProducts.size());
        assertEquals("Electronics", filteredProducts.get(0).getCategory());
        assertEquals("Electronics", filteredProducts.get(1).getCategory());
    }

    @Test
    void shouldReturnEmptyListIfNoProductsMatchCategory() {
        List<Product> products = Arrays.asList(
                new Product("SKU0001", new BigDecimal("19.99"), "Wireless Mouse", "Electronics"),
                new Product("SKU0003", new BigDecimal("29.50"), "Water Bottle", "Home & Kitchen")
        );

        List<Product> filteredProducts = filters.filterByCategory(products, "Clothing");

        assertEquals(0, filteredProducts.size());
    }

    @Test
    void shouldBeCaseInsensitive() {
        List<Product> products = Arrays.asList(
                new Product("SKU0001", new BigDecimal("19.99"), "Wireless Mouse", "electronics"),
                new Product("SKU0002", new BigDecimal("499.00"), "4K Smart TV", "Electronics"),
                new Product("SKU0003", new BigDecimal("29.50"), "Water Bottle", "Home & Kitchen")
        );

        List<Product> filteredProducts = filters.filterByCategory(products, "electronics");

        assertEquals(2, filteredProducts.size());
    }

    @Test
    void shouldReturnEmptyListForEmptyProductList() {
        List<Product> filteredProducts = filters.filterByCategory(List.of(), "Electronics");

        assertEquals(0, filteredProducts.size());
    }
}