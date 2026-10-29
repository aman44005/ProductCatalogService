package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {

    @GetMapping("/products")
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setId(1L);
        product.setName("Test_Product");
        products.add(product);
        return products;
    }

    @GetMapping("/products/{id}")
    public Product getProduct(@PathVariable("id") Long product_id) {
        Product product = new Product();
        product.setId(product_id);
        return product;
    }
}
