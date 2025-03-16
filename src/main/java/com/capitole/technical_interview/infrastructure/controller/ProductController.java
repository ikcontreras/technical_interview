package com.capitole.technical_interview.infrastructure.controller;

import com.capitole.technical_interview.application.service.ProductService;
import com.capitole.technical_interview.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

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

        if (category != null) {
            category = java.net.URLDecoder.decode(category, StandardCharsets.UTF_8);
            products = productService.getByCategory(category);
        }

        if (sortBy != null) {
            sortBy = java.net.URLDecoder.decode(sortBy, StandardCharsets.UTF_8);
            products = productService.findAllSorted(sortBy, order.equalsIgnoreCase("desc"));
        }

        return ResponseEntity.ok(products);
    }
}
