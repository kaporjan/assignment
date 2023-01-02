package com.an.assignment.mapper;

import com.an.assignment.domain.AccountDetails;
import com.an.assignment.domain.CreateAccountRequest;
import com.an.assignment.entity.Account;
import com.an.assignment.entity.Customer;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class AccountMapper {
    public Account map(Customer customer, CreateAccountRequest createAccountRequest) {
        Account account = new Account();
        account.setName(createAccountRequest.getName());
        account.setCustomer(customer);
        return account;
    }

    public AccountDetails map(Account account, BigDecimal balance) {
        return AccountDetails.builder()
                .id(account.getId())
                .customerId(account.getCustomer().getId())
                .name(account.getName())
                .balance(balance)
                .build();
    }
}
