package com.example.paymentsystem.models;

import com.example.paymentsystem.enums.TransactionStatus;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@DiscriminatorValue("CHARGE")
@Getter
@Setter
public class ChargeTransaction extends Transaction {

    public ChargeTransaction() {
    }

    public ChargeTransaction(Merchant merchant, BigDecimal amount, String customerEmail, String customerPhone, String referenceId) {
        super(merchant, amount, TransactionStatus.APPROVED, customerEmail, customerPhone, referenceId);
    }
}
