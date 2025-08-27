package dev.java.ecommerce.basketservice.controller;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService _productService;

    public ProductController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<PlatziProductResponse>> getAllProducts(){
        return ResponseEntity.ok(_productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlatziProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(_productService.getProductById(id));
    }
}
