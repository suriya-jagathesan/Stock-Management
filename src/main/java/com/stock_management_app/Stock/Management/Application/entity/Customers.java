package com.stock_management_app.Stock.Management.Application.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customers {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;
    private String customer_name;
    @Column(unique = true, nullable = false)
    private String email;
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private CustomerStatus status;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
        status = CustomerStatus.ACTIVE;
    }
    public enum CustomerStatus {
        ACTIVE, INACTIVE
    }
}
