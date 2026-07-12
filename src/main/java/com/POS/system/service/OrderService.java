package com.POS.system.service;

import com.POS.system.Domain.OrderStatus;
import com.POS.system.Domain.PaymentType;
import com.POS.system.payload.dto.OrderDto;

import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto) throws Exception;
    OrderDto getOrderById(Long id) throws Exception;
    List<OrderDto> getOrderByBranch(Long branchId,
                                    Long customerId,
                                    Long cashierId,
                                    PaymentType paymentType,
                                    OrderStatus status) throws Exception;


    List<OrderDto> getOrderByCashier(Long cashierId) throws Exception;
    void deleteOrder(Long id) throws Exception;
    List<OrderDto> getTodayOrdersByBranch(Long branchId) throws Exception;
    List<OrderDto> getOrdersByCustomerId(Long customerId) throws Exception;
    List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId) throws Exception;
}
