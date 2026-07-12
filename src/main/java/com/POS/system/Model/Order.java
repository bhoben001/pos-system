package com.POS.system.Model;


import com.POS.system.Domain.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    @ManyToOne
    private  Branch branch;

    @ManyToOne
    private  User cashier ;

    @ManyToOne
    private  Customer customer;

    @OneToMany(mappedBy = "order",cascade = CascadeType.ALL)
    private List<OrderItem> items;

    private PaymentType paymentType;

    @PrePersist
    private void onCreate() {
        createdAt = LocalDateTime.now();
    }


}
