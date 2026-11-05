package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.dtos.CategoryDto;
import com.example.productcatalogservice.dtos.ProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import com.example.productcatalogservice.services.IProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long product_id) {
        if(product_id <= 0L){
            throw new IllegalArgumentException("id can not be null or negative");
        }

        Product product = productService.getProductDetailsById(product_id);

       if(product == null){
           throw new RuntimeException("product not found");
       }

        ProductDto productDto = from(product);
        return new ResponseEntity<>(productDto, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<Product> allProducts = productService.getAllProducts();

        if(allProducts == null){
            throw new RuntimeException("Products not found");
        }


        List<ProductDto> productDtos = allProducts.stream()
                .map(x -> from(x))
                .collect(Collectors.toList());

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ProductDto replaceProduct(@PathVariable Long id , @RequestBody ProductDto dto){
        if(id <= 0L || dto == null){
            throw new IllegalArgumentException("id or request dto is not correct");
        }

        Product inputproduct = from(dto);
        Product outProduct = productService.replaceProduct(id,inputproduct);

        if(outProduct == null)
        {
            throw new RuntimeException("product not found");
        }

        return from(outProduct);

    }

    private Product from(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setImageUrl(productDto.getImageUrl());
        product.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            Category category = new Category();
            category.setName(productDto.getCategory().getName());
            category.setId(productDto.getCategory().getId());
            product.setCategory(category);
        }
        return product;
    }
    private ProductDto from(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setImageUrl(product.getImageUrl());
        if(product.getCategory() != null) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setDescription(product.getCategory().getDescription());
            categoryDto.setName(product.getCategory().getName());
            categoryDto.setId(product.getCategory().getId());
            productDto.setCategory(categoryDto);
        }

        return productDto;
    }
}
