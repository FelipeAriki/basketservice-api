package dev.java.ecommerce.basketservice.repository;

import dev.java.ecommerce.basketservice.entity.Basket;
import dev.java.ecommerce.basketservice.enums.ProductStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BasketRepository extends MongoRepository<Basket, String> {
    Optional<Basket> findByClientAndStatus(Long client, ProductStatus status);
}
