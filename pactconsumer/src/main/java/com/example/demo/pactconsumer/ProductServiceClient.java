package com.example.demo.pactconsumer;

import com.example.demo.pactconsumer.model.Product;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductServiceClient {

    private final RestTemplate restTemplate;

    public ProductServiceClient(@Value("${productservice.baseurl}") String baseUrl) {
        this.restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
    }


    public Product getProductById(Integer productId) {
        return restTemplate.getForObject("/products/" + productId, Product.class);
    }

}
