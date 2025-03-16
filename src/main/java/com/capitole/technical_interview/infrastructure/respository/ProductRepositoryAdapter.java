package com.capitole.technical_interview.infrastructure.respository;

import com.capitole.technical_interview.domain.model.Product;
import com.capitole.technical_interview.infrastructure.entity.ProductEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepositoryAdapter implements com.capitole.technical_interview.application.port.ProductRepository {

    private final JpaProductRepository jpaRepository;

    public ProductRepositoryAdapter(JpaProductRepository jpaRepository) {
        this.jpaRepository = jpaRepository;
    }

    @Override
    public List<Product> findAll() {
        return jpaRepository.findAll().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    private Product toDomain(ProductEntity entity) {
        return new Product(entity.getSku(), entity.getPrice(), entity.getDescription(), entity.getCategory());
    }
}
