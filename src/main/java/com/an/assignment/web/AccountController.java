package com.an.assignment.web;

import com.an.assignment.domain.AccountDetails;
import com.an.assignment.domain.CreateAccountRequest;
import com.an.assignment.exception.NotFoundException;
import com.an.assignment.service.AccountService;
import com.an.assignment.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
@RequestMapping(path = "/customer/{customerId}/account", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final CustomerService customerService;
    private final AccountService accountService;

    public AccountController(CustomerService customerService, AccountService accountService) {
        this.customerService = customerService;
        this.accountService = accountService;
    }


    @GetMapping
    Page<AccountDetails> getAccounts(@PathVariable("customerId") Long customerId, Pageable pageable) {
        return accountService.getAccounts(customerId, pageable);
    }


    @GetMapping("{accountId}")
    AccountDetails getAccount(@PathVariable("customerId") Long customerId,
                                    @PathVariable("accountId") Long accountId, Pageable pageable) {
        return accountService.getAccount(customerId, accountId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    AccountDetails createAccount(@Valid @RequestBody CreateAccountRequest createAccountRequest) {
        return accountService.createAccount(createAccountRequest);
    }

    @DeleteMapping("{accountId}")
    @ResponseStatus(HttpStatus.OK)
    void deleteCustomer(@PathVariable("accountId") final Long accountId) {
        try {
            accountService.deleteAccount(accountId);
        } catch (
                EmptyResultDataAccessException e) {
            throw new NotFoundException(e);
        }
    }
}
