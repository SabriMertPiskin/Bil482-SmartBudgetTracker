package com.smart_budget_tracker.bil482.usecase_2.budget;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "budgets")
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String category;
    @Column(nullable = false)
    private BigDecimal amount;
    @Column(nullable = false)
    private String strategyType; // "MONTHLY", "WEEKLY"
}