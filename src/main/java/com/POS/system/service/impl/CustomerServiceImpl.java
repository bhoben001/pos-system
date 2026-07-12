package com.POS.system.service.impl;

import com.POS.system.Model.Customer;
import com.POS.system.repository.CustomerRepository;
import com.POS.system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Long id, Customer user) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        if(user.getFullName()!=null)customer.setFullName(user.getFullName());
        if(user.getEmail()!=null)customer.setEmail(user.getEmail());
        if(user.getPhone()!=null)customer.setPhone(user.getPhone());
        return customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        customerRepository.delete(customer);
    }

    @Override
    public Customer getCustomer(Long id) throws Exception {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new Exception("Customer not found"));
        return customer;
    }

    @Override
    public List<Customer> getAllCustomers() throws Exception {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> searchCustomers(String keyword) throws Exception {
        return customerRepository.findByFullNameContainingIgnoreCaseOrEmailContainingIgnoreCase(keyword, keyword);
    }
}













