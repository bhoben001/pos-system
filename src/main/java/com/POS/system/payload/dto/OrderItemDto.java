package com.POS.system.payload.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemDto {

    private Long id;

    private Integer quantity;

    private Double price;


    private ProductDto product;

    private Long productId;

    private Long orderId;


}
