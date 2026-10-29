package com.example.productcatalogservice.controllers;

import com.example.productcatalogservice.models.Category;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.catalog.Catalog;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryController {



    @GetMapping("/categories")
    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        Category category = new Category();
        category.setId(1L);
        category.setName("Test_Category_1");
        categories.add(category);
        category = new Category();
        category.setId(2L);
        category.setName("Test_Category_2");
        categories.add(category);
        return categories;
    }
}
