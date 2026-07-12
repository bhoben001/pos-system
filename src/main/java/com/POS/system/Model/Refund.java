package com.POS.system.Model;

import com.POS.system.Domain.PaymentType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Refund {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Order order;

    private String reason;

    private Double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private ShiftReport shiftReport;

    @ManyToOne (fetch = FetchType.LAZY)
    private User cashier;





    @ManyToOne  (fetch = FetchType.LAZY)
    private Branch branch;

    private PaymentType paymentType;
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
    }


}
