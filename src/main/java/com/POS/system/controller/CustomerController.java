package com.POS.system.controller;

import com.POS.system.Model.Customer;
import com.POS.system.payload.response.ApiResponse;
import com.POS.system.service.impl.CustomerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/customer")
public class CustomerController {

    private final CustomerServiceImpl customerService;

    @PostMapping
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        return  ResponseEntity.ok().body(customerService.createCustomer(customer));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customer) throws Exception {
        return  ResponseEntity.ok().body(customerService.updateCustomer(id,customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteCustomer(@PathVariable Long id) throws Exception {
        customerService.deleteCustomer(id);
        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setMessage("Customer deleted successfully");
        return  ResponseEntity.ok().body(apiResponse);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> getAllCustomer() throws Exception {
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @GetMapping("/search")
    public  ResponseEntity<List<Customer>> searchCustomers(@RequestParam String keyword) throws Exception{
        return ResponseEntity.ok(customerService.searchCustomers(keyword));
    }
}
