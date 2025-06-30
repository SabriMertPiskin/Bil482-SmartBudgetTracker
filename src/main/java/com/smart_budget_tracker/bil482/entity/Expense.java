package com.smart_budget_tracker.bil482.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Çağan Durgun
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "expenses")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title; // Harcama başlığı veya kısa açıklaması

    @Column(nullable = false)
    private BigDecimal amount; // Harcama tutarı

    @Column(nullable = false)
    private LocalDateTime date; // Harcama tarihi

    @Column(nullable = false)
    private String category; // Harcama kategorisi (örn: Yemek, Ulaşım)

    @PrePersist
    protected void onCreate() {
        if (date == null) {
            date = LocalDateTime.now();
        }
    }
}
