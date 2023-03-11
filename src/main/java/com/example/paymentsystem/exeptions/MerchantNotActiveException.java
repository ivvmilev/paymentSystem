package com.example.paymentsystem.exeptions;

public class MerchantNotActiveException extends RuntimeException {
    public MerchantNotActiveException(String message) {
        super(message);
    }
}