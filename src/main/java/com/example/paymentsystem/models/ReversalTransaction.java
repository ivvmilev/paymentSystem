package com.example.paymentsystem.models;

import com.example.paymentsystem.enums.TransactionStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("REVERSAL")
@Getter
@Setter
public class ReversalTransaction extends Transaction {

    public ReversalTransaction() {
    }

    public ReversalTransaction(Merchant merchant, BigDecimal amount, String customerEmail, String customerPhone, String referenceId) {
        super(merchant, amount, TransactionStatus.REVERSED, customerEmail, customerPhone, referenceId);
    }
}
