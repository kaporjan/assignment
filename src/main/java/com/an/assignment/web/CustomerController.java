package com.an.assignment.web;

import com.an.assignment.domain.CreateCustomerRequest;
import com.an.assignment.domain.CustomerDetails;
import com.an.assignment.exception.NotFoundException;
import com.an.assignment.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/customer", produces = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }


    @GetMapping("{customerId}")
    CustomerDetails getCustomer(@PathVariable("customerId") Long customerId) {
        return customerService.getCustomer(customerId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CustomerDetails createCustomer(@Valid @RequestBody CreateCustomerRequest createCustomerRequest) {
        return customerService.createCustomer(createCustomerRequest);
    }

    @DeleteMapping("{customerId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCustomer(@PathVariable("customerId") final Long customerId) {
        try {
            customerService.deleteCustomer(customerId);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NotFoundException(e);
        }
    }
}
