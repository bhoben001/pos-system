package com.POS.system.Mapper;

import com.POS.system.Model.OrderItem;
import com.POS.system.payload.dto.OrderItemDto;

public class OrderItemMapper {
    public static OrderItemDto toDto(OrderItem item){
        if(item == null) return null;
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProduct().getId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .product(ProductMapper.toDto(item.getProduct()))
                .build();

    }
}

