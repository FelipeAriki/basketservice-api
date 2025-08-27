package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.PlatziStoreClient;
import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final PlatziStoreClient _platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient){
        _platziStoreClient = platziStoreClient;
    }


    public List<PlatziProductResponse> getAllProducts(){
        return _platziStoreClient.getAllProducts();
    }

    public PlatziProductResponse getProductById(Long id){
        return _platziStoreClient.getProductById(id);
    }
}
