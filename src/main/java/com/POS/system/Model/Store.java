package com.POS.system.Model;

import com.POS.system.Domain.StoreStatus;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode

@JsonPropertyOrder({"id","brand","storeAdmin","createdAt","updatedAt","description","storeType","status","contact"})
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @OneToOne
    private User storeAdmin;

    @Column(updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    @Embedded
    private StoreContact contact=new StoreContact();

    @PrePersist
    protected void onCreate(){
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
        status = StoreStatus.PENDING;
    }

    @PreUpdate
    protected void onUpdate(){
        updatedAt = LocalDateTime.now();
    }

}
