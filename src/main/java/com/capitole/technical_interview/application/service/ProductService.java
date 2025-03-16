package com.capitole.technical_interview.application.service;

import com.capitole.technical_interview.application.port.ProductRepository;
import com.capitole.technical_interview.domain.model.Product;
import com.capitole.technical_interview.domain.service.Filters;
import com.capitole.technical_interview.domain.service.SortProducts;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final Filters filters;
    private final SortProducts sortProducts;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getByCategory(String category) {
        List<Product> products = productRepository.findAll();
        return filters.filterByCategory(products, category);
    }

    public List<Product> findAllSorted(String sortBy, boolean ascending) {
        List<Product> products = productRepository.findAll();
        return sortProducts.sort(products, sortBy, ascending);
    }
}
