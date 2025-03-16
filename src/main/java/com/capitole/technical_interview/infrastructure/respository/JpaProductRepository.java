package com.capitole.technical_interview.infrastructure.respository;

import com.capitole.technical_interview.infrastructure.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {}
