package com.example.paymentsystem.controllers;

import com.example.paymentsystem.exeptions.InvalidTransactionStatusException;
import com.example.paymentsystem.exeptions.MerchantNotActiveException;
import com.example.paymentsystem.models.Transaction;
import com.example.paymentsystem.services.interfaces.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/authorize")
    public ResponseEntity<Transaction> authorizeTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction authorizedTransaction = transactionService.authorizeTransaction(transaction);
            return ResponseEntity.ok(authorizedTransaction);
        } catch (MerchantNotActiveException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/charge")
    public ResponseEntity<Transaction> chargeTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction chargedTransaction = transactionService.chargeTransaction(transaction);
            return ResponseEntity.ok(chargedTransaction);
        } catch (MerchantNotActiveException | InvalidTransactionStatusException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/refund")
    public ResponseEntity<Transaction> refundTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction refundedTransaction = transactionService.refundTransaction(transaction);
            return ResponseEntity.ok(refundedTransaction);
        } catch (MerchantNotActiveException | InvalidTransactionStatusException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/reverse")
    public ResponseEntity<Transaction> reverseTransaction(@RequestBody Transaction transaction) {
        try {
            Transaction reversedTransaction = transactionService.reverseTransaction(transaction);
            return ResponseEntity.ok(reversedTransaction);
        } catch (MerchantNotActiveException | InvalidTransactionStatusException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> getTransactionById(@PathVariable Long id) {
        Transaction transaction = transactionService.getTransactionById(id);
        if (transaction != null) {
            return ResponseEntity.ok(transaction);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getAllTransactions() {
        List<Transaction> transactions = transactionService.getAllTransactions();
        return ResponseEntity.ok(transactions);
    }
}