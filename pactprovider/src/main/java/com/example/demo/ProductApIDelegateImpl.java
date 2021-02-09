package com.example.demo;

import com.example.demo.api.ProductsApiDelegate;
import com.example.demo.model.ProductResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductApIDelegateImpl implements ProductsApiDelegate {

    @Override
    public ResponseEntity<ProductResponse> getProduct(Long productId) {
        ProductResponse response = new ProductResponse();
        response.setId(1L);
        response.setName("Product name for Demo");
        return ResponseEntity.ok(response);
    }
}
