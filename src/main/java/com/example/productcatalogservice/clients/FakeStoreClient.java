package com.example.productcatalogservice.clients;

import com.example.productcatalogservice.dtos.FakeStoreProductDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Component
public class FakeStoreClient {

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    public <T> ResponseEntity<T> requestForEntity(HttpMethod httpMethod, String url, @Nullable Object request, Class<T> responseType, Object... uriVariables) throws RestClientException {
        RestTemplate restTemplate = restTemplateBuilder.build();
        RequestCallback requestCallback = restTemplate.httpEntityCallback(request, responseType);
        ResponseExtractor<ResponseEntity<T>> responseExtractor =
                restTemplate.responseEntityExtractor(responseType);
        return restTemplate.execute(url, httpMethod, requestCallback,
                responseExtractor, uriVariables);
    }

    private <T> Boolean validateResponse(ResponseEntity<T> fakeStoreProductDtoResponseEntity) {
        return fakeStoreProductDtoResponseEntity.hasBody() &&
                fakeStoreProductDtoResponseEntity.getStatusCode()
                        .equals(HttpStatusCode.valueOf(200));
    }

    public FakeStoreProductDto getProductDetailsById(Long id) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoRes =
                requestForEntity(
                        HttpMethod.GET,
                        "https://fakestoreapi.com/products/{id}",
                        null,                         // Request body
                        FakeStoreProductDto.class,     // Response type
                        id
                );
        if(validateResponse(fakeStoreProductDtoRes))return fakeStoreProductDtoRes.getBody();

        return null;
    }

    public List<FakeStoreProductDto> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> allFakestoreProductDtoRes =
                requestForEntity(
                        HttpMethod.GET,
                        "https://fakestoreapi.com/products",
                        null,
                        FakeStoreProductDto[].class
                );

        if(validateResponse(allFakestoreProductDtoRes) && allFakestoreProductDtoRes.getBody() != null)
            return List.of(allFakestoreProductDtoRes.getBody());

        return null;
    }

    public FakeStoreProductDto replaceProduct(Long id, FakeStoreProductDto fakeStoreProduct) {
        ResponseEntity<FakeStoreProductDto> fakeStoreProductDtoRes =
                requestForEntity(
                        HttpMethod.PUT,
                        "https://fakestoreapi.com/products/{id}",
                        fakeStoreProduct,
                        FakeStoreProductDto.class,
                        id
                );


        if(validateResponse(fakeStoreProductDtoRes))
        {
            return fakeStoreProductDtoRes.getBody();
        }

        return null;
    }

}
