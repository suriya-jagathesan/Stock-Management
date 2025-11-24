package com.stock_management_app.Stock.Management.Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "invoices")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Subscription subscription;

    private String invoiceNumber;

    private LocalDate invoiceDate;

    private LocalDate billingCycleStart;

    private LocalDate billingCycleEnd;

    private Double fixedCharge;

    private Double usageCharge;

    private Double totalAmount;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        invoiceDate = LocalDate.now();
        paymentStatus = PaymentStatus.PENDING;
    }

    public enum PaymentStatus {
        PENDING, PAID
    }
}
