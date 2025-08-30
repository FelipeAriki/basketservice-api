package dev.java.ecommerce.basketservice.service;

public class BusinessException extends RuntimeException{
    public BusinessException(String message){
        super(message);
    }
}
