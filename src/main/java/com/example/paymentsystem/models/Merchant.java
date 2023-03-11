package com.example.paymentsystem.models;

import com.example.paymentsystem.enums.MerchantStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

import java.math.BigDecimal;

@Entity
@Table(name = "merchants")
@Getter
@Setter
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Description is required")
    private String description;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotBlank(message = "Status is required")
    private MerchantStatus status;

    @NotNull(message = "Total transaction sum is required")
    @DecimalMin(value = "0", inclusive = false, message = "Total transaction sum must be greater than zero")
    private BigDecimal totalTransactionSum;
}