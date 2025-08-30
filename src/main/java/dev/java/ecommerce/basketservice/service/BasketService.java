package dev.java.ecommerce.basketservice.service;

import dev.java.ecommerce.basketservice.client.response.PlatziProductResponse;
import dev.java.ecommerce.basketservice.controller.request.BasketRequest;
import dev.java.ecommerce.basketservice.controller.request.PaymentRequest;
import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.entity.Product;
import dev.java.ecommerce.basketservice.enums.ProductStatus;
import dev.java.ecommerce.basketservice.exceptions.DataNotFoundException;
import dev.java.ecommerce.basketservice.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BasketService {
    private final BasketRepository _basketRepository;
    private final ProductService _productService;

    BasketService(BasketRepository basketRepository, ProductService productService){
        _basketRepository = basketRepository;
        _productService = productService;
    }

    public Basket getBasketById(String id){
        return _basketRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Basket not found"));
    }

    public Basket createBasket(BasketRequest basketRequest){
        _basketRepository.findByClientAndStatus(basketRequest.clientId(), ProductStatus.OPEN).ifPresent(basket -> {
            throw new BusinessException("There is already an open basket for this client");
        });

        List<Product> products = getProducts(basketRequest);

        Basket basket = Basket.builder()
                .client(basketRequest.clientId())
                .status(ProductStatus.OPEN)
                .products(products).build();
        basket.calculateTotalPrice();
        return _basketRepository.save(basket);
    }

    public Basket updateBasket(String id, BasketRequest basketRequest){
        Basket savedBasket = getBasketById(id);

        List<Product> products = getProducts(basketRequest);

        savedBasket.setProducts(products);
        savedBasket.calculateTotalPrice();
        return _basketRepository.save(savedBasket);
    }

    public Basket payBasket(String id, PaymentRequest paymentRequest){
        Basket savedBasket = getBasketById(id);
        savedBasket.setPaymentMethod(paymentRequest.getPaymentMethod());
        savedBasket.setStatus(ProductStatus.SOLD);
        return _basketRepository.save(savedBasket);
    }

    public void deleteBasket(String id){
        _basketRepository.delete(getBasketById(id));
    }

    private List<Product> getProducts(BasketRequest basketRequest){
        List<Product> products = new ArrayList<>();
        basketRequest.products().forEach(productRequest -> {
            PlatziProductResponse platziProductResponse = _productService.getProductById(productRequest.id());
            products.add(Product.builder()
                    .id(platziProductResponse.id())
                    .title(platziProductResponse.title())
                    .price(platziProductResponse.price())
                    .quantity(productRequest.quantity())
                    .build());
        });
        return products;
    }
}
