package com.an.assignment.service;

import com.an.assignment.domain.CreateAccountRequest;
import com.an.assignment.domain.CreateCustomerRequest;
import com.an.assignment.domain.CustomerDetails;
import com.an.assignment.entity.Customer;
import com.an.assignment.exception.NotFoundException;
import com.an.assignment.mapper.CustomerMapper;
import com.an.assignment.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AccountService accountService;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository, AccountService accountService, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.accountService = accountService;
        this.customerMapper = customerMapper;
    }

    @Override
    public CustomerDetails createCustomer(CreateCustomerRequest createCustomerRequest) {
        Customer customer = customerRepository.save(customerMapper.map(createCustomerRequest));
        if (!BigDecimal.ZERO.equals(createCustomerRequest.getBalance())) {
            accountService.createAccount(CreateAccountRequest.builder()
                            .customerId(customer.getId())
                            .balance(createCustomerRequest.getBalance())
                            .name("Default account")
                    .build());
        }
        return customerMapper.map(customer);
    }

    @Override
    public CustomerDetails getCustomer(Long customerId) {
        return customerRepository.findById(customerId)
                .map(customerMapper::map)
                .orElseThrow(NotFoundException::new);
    }

    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
