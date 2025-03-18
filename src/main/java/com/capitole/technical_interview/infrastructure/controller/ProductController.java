package com.capitole.technical_interview.infrastructure.controller;

import com.capitole.technical_interview.application.service.ProductService;
import com.capitole.technical_interview.domain.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@Tag(name = "Product API", description = "Operations related to product management")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(
        summary = "Retrieve all products",
        description = "Returns a list of all products, optionally filtered by category and sorted by SKU, price, description, or category."
    )
    @GetMapping
    public List<Product> getAllProducts(
            @Parameter(
                name = "category",
                description = "Filter products by category (e.g., Electronics, Home & Kitchen)",
                example = "Electronics"
            )
            @RequestParam(required = false) String category,
            @Parameter(
                name = "sortBy",
                description = "Sort products by field (sku, price, description, category)",
                schema = @Schema(defaultValue = "sku"),
                example = "price"
            )
            @RequestParam(defaultValue = "sku") String sortBy,
            @Parameter(
                name = "order",
                description = "Sort order: 'asc' (ascending) or 'desc' (descending)",
                schema = @Schema(defaultValue = "asc"),
                example = "desc"
            )
            @RequestParam(defaultValue = "asc") String order) {

        List<Product> products = productService.getAllProducts();

        if (category != null && !category.isEmpty()) {
            products = productService.getByCategory(products, category);
        }

        boolean isDescending = order.equalsIgnoreCase("asc");
        products = productService.findAllSorted(products, sortBy, isDescending);

        return products;
    }
}
