package com.capitole.technical_interview.application.service;

import com.capitole.technical_interview.application.port.ProductRepository;
import com.capitole.technical_interview.domain.discount.DiscountRule;
import com.capitole.technical_interview.domain.filter.FilterService;
import com.capitole.technical_interview.domain.model.Product;
import com.capitole.technical_interview.domain.sort.SortService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private final FilterService<Product> filters;
    private final SortService<Product> sortProducts;
    private final List<DiscountRule> discountRules;

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        applyDiscounts(products);
        return products;
    }

    public List<Product> getByCategory(List<Product> products, String category) {
        return filters.filterByCategory(products, category);
    }

    public List<Product> findAllSorted(List<Product> products,String sortBy, boolean ascending) {
        return sortProducts.sort(products, sortBy, ascending);
    }

    private void applyDiscounts(List<Product> products) {
        for (Product product : products) {
            for (DiscountRule rule : discountRules) {
                rule.applyDiscount(product);
            }
        }
    }
}
