package com.POS.system.payload.dto;

import com.POS.system.Domain.PaymentType;
import com.POS.system.Model.Branch;
import com.POS.system.Model.Customer;
import com.POS.system.Model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;

    private Double totalAmount;

    private LocalDateTime createdAt;

    private Long branchId;

    private Long customerId;

    private Branch branch;

    private UserDto cashier ;


    private Customer customer;

    private List<OrderItemDto> items;

    private PaymentType paymentType;
}
