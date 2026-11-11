package com.example.productcatalogservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Product extends BaseModel {
    @ManyToOne
    private Category category;
    private Double price;
    private String description;
    private String imageUrl;
    private String specification;
    private Double commission;
    private Boolean isPrimeSaleSpecific;

}
