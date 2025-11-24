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
@Table(name = "subscriptions")
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    private Customers customer;

    @ManyToOne(optional = false)
    private Plan plan;

    private LocalDate startDate;

    private LocalDate endDate;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDate nextBillingDate;

    private Integer currentUsage = 0;

    private Integer remainingUsage;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        status = Status.TRIAL;
        startDate = LocalDate.now();
        nextBillingDate = LocalDate.now().plusMonths(1);
        remainingUsage = plan.getUsageLimit();
    }

    public enum Status {
        TRIAL, ACTIVE, GRACE, CANCELLED
    }
}
