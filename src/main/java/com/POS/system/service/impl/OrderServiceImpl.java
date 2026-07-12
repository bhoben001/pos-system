package com.POS.system.service.impl;

import com.POS.system.Domain.OrderStatus;
import com.POS.system.Domain.PaymentType;
import com.POS.system.Mapper.OrderMapper;
import com.POS.system.Model.*;
import com.POS.system.payload.dto.OrderDto;
import com.POS.system.repository.OrderRepository;
import com.POS.system.repository.ProductRepository;
import com.POS.system.service.OrderService;
import com.POS.system.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;
    private final ProductRepository productRepository;


    @Override
    public OrderDto createOrder(OrderDto orderDto) throws Exception {
        User cashier=userService.getCurrentUser();
        Branch branch=cashier.getBranch();
        if(branch==null) throw new Exception("cashier's branch not found");

        Order order = new Order();
        order.setBranch(branch);
        order.setCashier(cashier);
        order.setCustomer(orderDto.getCustomer());
        order.setPaymentType(orderDto.getPaymentType());

        List<OrderItem> orderItems = orderDto.getItems().stream().map(
                itemDto->{
                    Product product=productRepository.findById(itemDto.getProductId()).orElseThrow(()->new EntityNotFoundException("product not found"));

                    return OrderItem.builder()
                            .product(product)
                            .quantity(itemDto.getQuantity())
                            .price(product.getSellingPrice() * itemDto.getQuantity())
                            .order(order)
                            .build();

                }
        ).toList();
        double total = orderItems.stream().mapToDouble(OrderItem::getPrice).sum();
        order.setTotalAmount(total);
        order.setItems(orderItems);
        Order savedOrder=orderRepository.save(order);
        return OrderMapper.toDto(savedOrder);
    }

    @Override
    public OrderDto getOrderById(Long id) throws Exception {
        Order order= orderRepository.findById(id).orElseThrow(()->new Exception("no order found with id "+id));
        return  OrderMapper.toDto(order);
    }

    @Override
    public List<OrderDto> getOrderByBranch(Long branchId,
                                           Long customerId,
                                           Long cashierId,
                                           PaymentType paymentType,
                                           OrderStatus status) {
        return orderRepository.findByBranchId(branchId).stream()
                .filter(order -> customerId==null ||
                        (order.getCustomer()!=null &&
                        order.getCustomer().getId().equals(customerId)))
                .filter(order -> cashierId==null ||
                        (order.getCashier()!=null &&
                                order.getCashier().getId().equals(cashierId)))
                .filter(order -> paymentType==null ||
                        (order.getPaymentType()==paymentType))
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrderByCashier(Long cashierId) {
        return orderRepository.findByCashierId(cashierId).stream()
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteOrder(Long id) throws Exception {
        Order order= orderRepository.findById(id).orElseThrow(()->new Exception("no order found with id "+id));
        orderRepository.delete(order);
    }

    @Override
    public List<OrderDto> getTodayOrdersByBranch(Long branchId){
        LocalDate today = LocalDate.now();
        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        return orderRepository.findByBranchIdAndCreatedAtBetween(branchId, start,end).stream()
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersByCustomerId(Long customerId){
        return orderRepository.findByCustomerId(customerId).stream()
                .map(OrderMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<OrderDto> getTop5RecentOrdersByBranchId(Long branchId){
        return orderRepository.findTop5ByBranchIdOrderByCreatedAtDesc(branchId).stream()
                .map(OrderMapper::toDto).collect(Collectors.toList());
    }


}
