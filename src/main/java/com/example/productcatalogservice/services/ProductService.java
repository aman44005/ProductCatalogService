package com.example.productcatalogservice.services;

import com.example.productcatalogservice.clients.FakeStoreClient;
import com.example.productcatalogservice.dtos.FakeStoreProductDto;
import com.example.productcatalogservice.models.Category;
import com.example.productcatalogservice.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService implements IProductService {

    @Autowired
    private FakeStoreClient fakeStoreClient;

    @Override
    public Product getProductDetailsById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.getProductDetailsById(id);
        if(fakeStoreProductDto != null)
            return from(fakeStoreProductDto);

        return null;
    }

    @Override
    public List<Product> getAllProducts() {
       List<FakeStoreProductDto> allFakeStoreProducts = fakeStoreClient.getAllProducts();

       if(allFakeStoreProducts != null)
           return allFakeStoreProducts.stream()
                                      .map(x->from(x))
                                      .collect(Collectors.toList());


        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        FakeStoreProductDto fakeStoreProductDto = fakeStoreClient.replaceProduct(id,from(product));

        if(fakeStoreProductDto != null)
            return from(fakeStoreProductDto);

        return null;
    }



    private FakeStoreProductDto from(Product product) {
        FakeStoreProductDto fakeStoreProductDto = new FakeStoreProductDto();
        fakeStoreProductDto.setId(product.getId());
        fakeStoreProductDto.setTitle(product.getName());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setImage(product.getImageUrl());
        if(product.getCategory() != null) {
            fakeStoreProductDto.setCategory(product.getCategory().getName());
        }
        return fakeStoreProductDto;
    }

    private Product from(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setName(fakeStoreProductDto.getTitle());
        product.setDescription(fakeStoreProductDto.getDescription());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setImageUrl(fakeStoreProductDto.getImage());
        Category category = new Category();
        category.setName(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
