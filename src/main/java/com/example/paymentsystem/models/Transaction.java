package com.example.paymentsystem.models;

import com.example.paymentsystem.enums.TransactionStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "transactions")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "transaction_type")
@Getter
@Setter
public abstract class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_phone")
    private String customerPhone;

    @Column(name = "reference_id")
    private String referenceId;

    public Transaction() {}

    public Transaction(Merchant merchant, BigDecimal amount, TransactionStatus status, String customerEmail, String customerPhone, String referenceId) {
        this.merchant = merchant;
        this.amount = amount;
        this.status = status;
        this.customerEmail = customerEmail;
        this.customerPhone = customerPhone;
        this.referenceId = referenceId;
    }
}

