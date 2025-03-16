package com.capitole.technical_interview.infrastructure.controller;

import com.capitole.technical_interview.application.service.ProductService;
import com.capitole.technical_interview.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Retrieve all products")
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts(
            @RequestParam(required = false) String category,
            @RequestParam(defaultValue = "sku") String sortBy,
            @RequestParam(defaultValue = "asc") String order) {

        List<Product> products = productService.getAllProducts();

        if (category != null && !category.isEmpty()) {
            products = productService.getByCategory(products, category);
        }

        boolean isDescending = order.equalsIgnoreCase("asc");
        products = productService.findAllSorted(products, sortBy, isDescending);

        return ResponseEntity.ok(products);
    }
}
