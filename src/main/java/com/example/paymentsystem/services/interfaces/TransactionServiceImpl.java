package com.example.paymentsystem.services.interfaces;

import com.example.paymentsystem.enums.MerchantStatus;
import com.example.paymentsystem.enums.TransactionStatus;
import com.example.paymentsystem.exeptions.InvalidTransactionStatusException;
import com.example.paymentsystem.exeptions.MerchantNotActiveException;
import com.example.paymentsystem.models.Merchant;
import com.example.paymentsystem.models.Transaction;
import com.example.paymentsystem.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final MerchantService merchantService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, MerchantService merchantService) {
        this.transactionRepository = transactionRepository;
        this.merchantService = merchantService;
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @Override
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Transaction authorizeTransaction(Transaction transaction) throws MerchantNotActiveException {
        Merchant merchant = merchantService.getMerchantById(transaction.getMerchant().getId())
                .orElseThrow(() -> new RuntimeException("Merchant not found"));
        if (merchant.getStatus() != MerchantStatus.ACTIVE) {
            throw new MerchantNotActiveException("Merchant is not active");
        }
        transaction.setStatus(TransactionStatus.AUTHORIZED);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction approveTransaction(Transaction transaction) throws InvalidTransactionStatusException {
        if (transaction.getStatus() != TransactionStatus.AUTHORIZED) {
            throw new InvalidTransactionStatusException("Transaction is not authorized");
        }
        transaction.setStatus(TransactionStatus.APPROVED);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction chargeTransaction(Transaction transaction) throws MerchantNotActiveException, InvalidTransactionStatusException {
        Merchant merchant = merchantService.getMerchantById(transaction.getMerchant().getId())
                .orElseThrow(() -> new RuntimeException("Merchant not found"));
        if (merchant.getStatus() != MerchantStatus.ACTIVE) {
            throw new MerchantNotActiveException("Merchant is not active");
        }
        if (transaction.getStatus() != TransactionStatus.APPROVED) {
            throw new InvalidTransactionStatusException("Transaction is not approved");
        }
        transaction.setStatus(TransactionStatus.CHARGED);
        merchant.setTotalTransactionSum(merchant.getTotalTransactionSum().add(transaction.getAmount()));
        merchantService.updateMerchant(merchant);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction refundTransaction(Transaction transaction) throws MerchantNotActiveException, InvalidTransactionStatusException {
        Merchant merchant = merchantService.getMerchantById(transaction.getMerchant().getId())
                .orElseThrow(() -> new RuntimeException("Merchant not found"));
        if (merchant.getStatus() != MerchantStatus.ACTIVE) {
            throw new MerchantNotActiveException("Merchant is not active");
        }
        if (transaction.getStatus() != TransactionStatus.APPROVED && transaction.getStatus() != TransactionStatus.CHARGED) {
            throw new InvalidTransactionStatusException("Transaction is not approved or charged");
        }
        transaction.setStatus(TransactionStatus.REFUNDED);
        merchant.setTotalTransactionSum(merchant.getTotalTransactionSum().subtract(transaction.getAmount()));
        merchantService.updateMerchant(merchant);
        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction reverseTransaction(Transaction transaction) throws MerchantNotActiveException, InvalidTransactionStatusException {
        Merchant merchant = merchantService.getMerchantById(transaction.getMerchant().getId())
                .orElseThrow(() -> new RuntimeException("Merchant not found"));
        if (merchant.getStatus() != MerchantStatus.ACTIVE) {
            throw new MerchantNotActiveException("Merchant is not active");
        }
        if (transaction.getStatus() != TransactionStatus.AUTHORIZED) {
            throw new InvalidTransactionStatusException("Transaction is not authorized");
        }
        transaction.setStatus(TransactionStatus.REVERSED);
        return transactionRepository.save(transaction);
    }
}