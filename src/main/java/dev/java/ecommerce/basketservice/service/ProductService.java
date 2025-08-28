package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {
    private final PlatziStoreClient _platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient){
        _platziStoreClient = platziStoreClient;
    }


    @Cacheable(value = "products")
    public List<PlatziProductResponse> getAllProducts(){
        log.info("Getting all products");
        return _platziStoreClient.getAllProducts();
    }

    @Cacheable(value = "product", key = "#productId")
    public PlatziProductResponse getProductById(Long productId){
        log.info("Getting product with id: {}", productId);
        return _platziStoreClient.getProductById(productId);
    }
}
