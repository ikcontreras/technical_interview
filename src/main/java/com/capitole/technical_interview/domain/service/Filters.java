package com.capitole.technical_interview.domain.service;

import com.capitole.technical_interview.domain.model.Product;

import java.util.List;
import java.util.stream.Collectors;

public class Filters {
    public List<Product> filterByCategory(List<Product> products, String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
