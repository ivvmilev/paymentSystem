package com.example.paymentsystem.exeptions;

public class ReversalTransactionException extends RuntimeException {
    public ReversalTransactionException(String message) {
        super(message);
    }
}