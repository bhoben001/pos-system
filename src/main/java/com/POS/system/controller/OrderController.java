package com.POS.system.controller;

import com.POS.system.Domain.OrderStatus;
import com.POS.system.Domain.PaymentType;
import com.POS.system.payload.dto.OrderDto;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping()
    ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) throws Exception {
       OrderDto createdOrder= orderService.createOrder(orderDto);
       return ResponseEntity.ok(createdOrder);
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) throws Exception {
        OrderDto order= orderService.getOrderById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/branch/{branchId}")
    ResponseEntity<List<OrderDto>> getOrderByBranch(@PathVariable Long branchId,
                                              @RequestParam(required = false) Long customerId,
                                              @RequestParam(required = false) Long cashierId,
                                              @RequestParam(required = false) PaymentType paymentType,
                                              @RequestParam(required = false) OrderStatus status
                                              ) throws Exception {
        List<OrderDto> order= orderService.getOrderByBranch(branchId,customerId,cashierId,paymentType,status);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/cashier/{id}")
    ResponseEntity< List<OrderDto>> getOrderByCashier(@PathVariable Long id) throws Exception {
        List<OrderDto> order= orderService.getOrderByCashier(id);
        return ResponseEntity.ok(order);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id) throws Exception {
        orderService.deleteOrder(id);
        ApiResponse  apiResponse = new ApiResponse();
        apiResponse.setMessage("Order has been deleted successfully");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/today/branch/{id}")
    ResponseEntity< List<OrderDto>> getTodayOrdersByBranch(@PathVariable Long id) throws Exception {
        List<OrderDto> orders= orderService.getTodayOrdersByBranch(id);
        return ResponseEntity.ok(orders);
    }

    @GetMapping("/customer/{id}")
    ResponseEntity< List<OrderDto>> getOrdersByCustomerId(@PathVariable Long id) throws Exception {
        List<OrderDto> order= orderService.getOrdersByCustomerId(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/recent/{branchId}")
    ResponseEntity<List<OrderDto> > getTop5RecentOrdersByBranchId(@PathVariable Long branchId) throws Exception {
        List<OrderDto>  order= orderService.getTop5RecentOrdersByBranchId(branchId);
        return ResponseEntity.ok(order);
    }

}
