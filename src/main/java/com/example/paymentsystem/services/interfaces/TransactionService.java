package com.example.paymentsystem.services.interfaces;

import com.example.paymentsystem.exeptions.InvalidTransactionStatusException;
import com.example.paymentsystem.models.Transaction;

import java.util.List;

public interface TransactionService {

    Transaction getTransactionById(Long id);

    List<Transaction> getAllTransactions();

    Transaction authorizeTransaction(Transaction transaction);

    Transaction chargeTransaction(Transaction transaction);

    Transaction refundTransaction(Transaction transaction);

    Transaction reverseTransaction(Transaction transaction);

    Transaction approveTransaction(Transaction transaction);
}