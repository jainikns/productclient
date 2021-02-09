package com.example.demo.pactconsumer;

import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.consumer.junit5.PactConsumerTestExt;
import au.com.dius.pact.consumer.junit5.PactTestFor;
import au.com.dius.pact.core.model.RequestResponsePact;
import au.com.dius.pact.core.model.annotations.Pact;
import au.com.dius.pact.core.model.annotations.PactFolder;
import com.example.demo.pactconsumer.model.Product;
import io.pactfoundation.consumer.dsl.LambdaDsl;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        properties = "productservice.baseurl:http://localhost:8082",
        classes = ProductServiceClient.class)
@ExtendWith(PactConsumerTestExt.class)
@PactTestFor(providerName = "pactprovider", port = "8082")
public class ProductServiceContractTest {

    @Autowired
    private ProductServiceClient productServiceClient;


    @Pact(consumer = "pactconsumer")
    public RequestResponsePact pactProductExists(PactDslWithProvider builder) {
        return builder.given(
                "Product 1 exists")
                .uponReceiving("A request to /products/1")
                .path("/products/1")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(LambdaDsl.newJsonBody((o) ->
                        o.stringType("name", "Product name for Demo")
                ).build()).toPact();
    }

    @PactTestFor(pactMethod = "pactProductExists")
    @Test
    public void productExists() {
        Product product = productServiceClient.getProductById(1);

        assertThat(product.getName()).isEqualTo("Product name for Demo");
    }
}
