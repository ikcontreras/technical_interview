package com.capitole.technical_interview.application.service;

import com.capitole.technical_interview.application.port.ProductRepository;
import com.capitole.technical_interview.domain.discount.DiscountRule;
import com.capitole.technical_interview.domain.model.Product;
import com.capitole.technical_interview.domain.filter.FilterProduct;
import com.capitole.technical_interview.domain.sort.SortProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @Mock
    private FilterProduct filters;

    @Mock
    private SortProduct sortProducts;

    @Mock
    private DiscountRule discountRule1;

    @Mock
    private DiscountRule discountRule2;

    @InjectMocks
    private ProductService productService;

    private static final List<Product> mockProducts = List.of(
            new Product("SKU0001", new BigDecimal("19.99"), "Wireless Mouse with ergonomic design", "Electronics"),
            new Product("SKU0002", new BigDecimal("499.00"), "4K Ultra HD Smart TV, 55 inches", "Electronics"),
            new Product("SKU0003", new BigDecimal("29.50"), "Stainless Steel Water Bottle, 1L", "Home & Kitchen"),
            new Product("SKU0004", new BigDecimal("15.00"), "Cotton T-Shirt, Unisex, Size M", "Clothing")
    );

    @BeforeEach
    void setUp() {
        productService = new ProductService(productRepository, filters, sortProducts, List.of(discountRule1, discountRule2));
    }

    @Test
    void testGetAllProducts() {
        when(productRepository.findAll()).thenReturn(mockProducts);

        List<Product> result = productService.getAllProducts();

        assertEquals(mockProducts.size(), result.size());
        assertEquals(mockProducts.get(0).getSku(), result.get(0).getSku());
        assertEquals(mockProducts.get(3).getSku(), result.get(3).getSku());

        verify(productRepository).findAll();
        verify(discountRule1, times(mockProducts.size())).applyDiscount(any(Product.class));
        verify(discountRule2, times(mockProducts.size())).applyDiscount(any(Product.class));
    }

    @Test
    void testGetByCategory() {
        String category = "Electronics";
        List<Product> filteredProducts = Arrays.asList(mockProducts.get(0), mockProducts.get(1));

        when(filters.filterByCategory(mockProducts, category)).thenReturn(filteredProducts);

        List<Product> result = productService.getByCategory(mockProducts, category);

        assertEquals(2, result.size());
        assertEquals("SKU0001", result.get(0).getSku());
        assertEquals("SKU0002", result.get(1).getSku());

        verify(filters, times(1)).filterByCategory(mockProducts, category);
    }

    @Test
    void testFindAllSortedByPriceAscending() {
        String sortBy = "price";
        boolean ascending = true;
        List<Product> sortedProducts = Arrays.asList(
                mockProducts.get(3),
                mockProducts.get(0),
                mockProducts.get(2),
                mockProducts.get(1)
        );

        when(sortProducts.sort(mockProducts, sortBy, ascending)).thenReturn(sortedProducts);

        List<Product> result = productService.findAllSorted(mockProducts, sortBy, ascending);

        assertEquals(4, result.size());
        assertEquals("SKU0004", result.get(0).getSku());
        assertEquals("SKU0002", result.get(3).getSku());

        verify(sortProducts, times(1)).sort(mockProducts, sortBy, ascending);
    }
}