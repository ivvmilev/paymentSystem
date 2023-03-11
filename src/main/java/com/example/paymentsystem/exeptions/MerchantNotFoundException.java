package com.example.paymentsystem.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MerchantNotFoundException extends RuntimeException {
    public MerchantNotFoundException(Long id) {
        super("Merchant not found with id: " + id);
    }
}