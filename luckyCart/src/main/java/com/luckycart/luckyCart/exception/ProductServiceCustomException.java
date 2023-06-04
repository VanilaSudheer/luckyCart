package com.luckycart.luckyCart.exception;

import lombok.Data;

@Data
public class ProductServiceCustomException extends RuntimeException{
    String errorCode;

    public ProductServiceCustomException(String message, String errorCode){
        super(message);
        this.errorCode = errorCode;
    }
}
