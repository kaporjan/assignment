package com.an.assignment.service;

import com.an.assignment.domain.CreateCustomerRequest;
import com.an.assignment.domain.CustomerDetails;

public interface CustomerService {
    CustomerDetails createCustomer(CreateCustomerRequest createCustomerRequest);

    CustomerDetails getCustomer(Long customerId);

    void deleteCustomer(Long customerId);
}
