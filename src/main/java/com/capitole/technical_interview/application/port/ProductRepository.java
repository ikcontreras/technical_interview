package com.capitole.technical_interview.application.port;

import com.capitole.technical_interview.domain.model.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    List<Product> findByCategory(String category);
    List<Product> findAllSorted(String sortBy, boolean ascending);
}
