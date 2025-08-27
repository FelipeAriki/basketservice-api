package dev.java.ecommerce.basketservice.controller;

import dev.java.ecommerce.basketservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private final ProductService _productService;

    public ProductController(ProductService productService){
        _productService = productService;
    }

    @GetMapping
    public ResponseEntity<Void> getAllProducts(){
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Void> getProductById(@PathVariable Long id){
        return ResponseEntity.ok().build();
    }
}
