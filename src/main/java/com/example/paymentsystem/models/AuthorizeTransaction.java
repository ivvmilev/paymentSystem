package com.example.paymentsystem.models;

import com.example.paymentsystem.enums.TransactionStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("AUTHORIZE")
@Getter
@Setter
public class AuthorizeTransaction extends Transaction {

    public AuthorizeTransaction() {
    }

    public AuthorizeTransaction(Merchant merchant, BigDecimal amount, String customerEmail, String customerPhone, String referenceId) {
        super(merchant, amount, TransactionStatus.AUTHORIZED, customerEmail, customerPhone, referenceId);
    }
}
