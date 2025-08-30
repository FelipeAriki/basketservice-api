package dev.java.ecommerce.basketservice.controller.request;

import dev.java.ecommerce.basketservice.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {

    private PaymentMethod paymentMethod;
}
