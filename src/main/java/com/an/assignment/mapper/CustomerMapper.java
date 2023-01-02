package com.an.assignment.mapper;

import com.an.assignment.domain.CreateCustomerRequest;
import com.an.assignment.domain.CustomerDetails;
import com.an.assignment.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public Customer map(CreateCustomerRequest createCustomerRequest) {
        Customer customer = new Customer();
        customer.setName(createCustomerRequest.getName());
        customer.setSurname(createCustomerRequest.getSurname());
        return customer;
    }

    public CustomerDetails map(Customer customer) {
        return CustomerDetails.builder()
                .id(customer.getId())
                .name(customer.getName())
                .surname(customer.getSurname())
                .build();
    }
}
