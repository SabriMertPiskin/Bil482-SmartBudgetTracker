package com.smart_budget_tracker.bil482.entity;

import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.smart_budget_tracker.bil482.enums.ExpenseType;

/**
 * Expense this is a Entity
 * 
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

    // Harcamanın adı veya kısa açıklaması
    @Column(nullable = false)
    private String title;

    // Harcama açıklaması (isteğe bağlı)
    private String description;

    // Harcama tutarı
    @Column(nullable = false)
    private BigDecimal amount;

    // Harcama tarihi
    @Column(nullable = false)
    private LocalDateTime date;

    // Harcama türü: FIXED, VARIABLE gibi, Factory Pattern için zemin hazırlar
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpenseType type;

    // Harcama kategorisi: yiyecek, ulaşım, sağlık vs.
    @Column(nullable = false)
    private String category;

    // Kullanıcının belirlediği bu harcama bir bütçe limitine dahil mi?
    private boolean budgeted;

    // Ödeme yöntemi: Nakit, Kredi Kartı, vs. (gerekirse enum yapılabilir)
    private String paymentMethod;

    // Kullanıcı tanımı: Multi-user support için hazır hale getirir
    @Column(nullable = false)
    private Long userId;

    // Silinme durumu (soft delete desteği için)
    private boolean deleted;

    // Eklenme zamanı
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    // Güncellenme zamanı
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
