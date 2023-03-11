package com.example.paymentsystem.models;

import com.example.paymentsystem.enums.TransactionStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("REFUND")
@Getter
@Setter
public class RefundTransaction extends Transaction {

    public RefundTransaction() {
    }

    public RefundTransaction(Merchant merchant, BigDecimal amount, String customerEmail, String customerPhone, String referenceId) {
        super(merchant, amount, TransactionStatus.REFUNDED, customerEmail, customerPhone, referenceId);
    }
}
