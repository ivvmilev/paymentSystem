package com.example.paymentsystem.exeptions;

public class InvalidMerchantException extends RuntimeException {
    public InvalidMerchantException(String message) {
        super(message);
    }
}