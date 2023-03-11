package com.example.paymentsystem.repositories;

import com.example.paymentsystem.models.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.TransactionStatus;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByMerchantId(Long merchantId);

    List<Transaction> findByMerchantIdAndStatusIn(Long merchantId, List<TransactionStatus> statuses);
}