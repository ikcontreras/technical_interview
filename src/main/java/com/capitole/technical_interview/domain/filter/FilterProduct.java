package com.capitole.technical_interview.domain.filter;

import com.capitole.technical_interview.domain.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class FilterProduct implements FilterService<Product> {
    @Override
    public List<Product> filterByCategory(List<Product> products, String category) {
        return products.stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
}
