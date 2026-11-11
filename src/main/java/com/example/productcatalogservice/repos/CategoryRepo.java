package com.example.productcatalogservice.repos;

import com.example.productcatalogservice.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category,Long> {
}
