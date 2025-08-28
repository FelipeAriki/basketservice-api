package dev.java.ecommerce.basketservice.entity;

import dev.java.ecommerce.basketservice.enums.ProductStatus;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collation = "basket")
public class Basket {
    @Id
    private String id;
    private Long client;
    private BigDecimal totalPrice;
    private List<Product> products;
    private ProductStatus status;

    public void calculateTotalPrice(){
        this.totalPrice = products.stream()
                .map(product -> product.getPrice().multiply(BigDecimal.valueOf(product.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}
