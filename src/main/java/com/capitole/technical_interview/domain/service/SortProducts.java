package com.capitole.technical_interview.domain.service;

import com.capitole.technical_interview.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SortProducts {
    public List<Product> sort(List<Product> products, String column, boolean ascending) {
        Comparator<Product> comparator = switch (column.toLowerCase()) {
            case "sku" -> Comparator.comparing(Product::getSku);
            case "price" -> Comparator.comparing(Product::getPrice);
            case "description" -> Comparator.comparing(Product::getDescription);
            case "category" -> Comparator.comparing(Product::getCategory);
            default -> throw new IllegalArgumentException("Invalid sort field: " + column);
        };

        if (!ascending) {
            comparator = comparator.reversed();
        }

        return products.stream()
                .sorted(comparator)
                .collect(Collectors.toList());
    }
}
