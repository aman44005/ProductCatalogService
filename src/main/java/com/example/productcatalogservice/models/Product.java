package com.example.productcatalogservice.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Product extends BaseModel {
    private Category category;
    private Double price;
    private String description;
    private String imageUrl;
    private String specification;
    private Double commission;
    private Boolean isPrimeSaleSpecific;

}
