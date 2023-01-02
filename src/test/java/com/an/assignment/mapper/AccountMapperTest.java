package com.an.assignment.mapper;

import com.an.assignment.domain.CreateAccountRequest;
import com.an.assignment.entity.Account;
import com.an.assignment.entity.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountMapperTest {
    @Test
    void canMap() {
        // given
        AccountMapper mapper = new AccountMapper();
        CreateAccountRequest createAccountRequest = CreateAccountRequest.builder()
                .name("NAME")
                .customerId(123L)
                .balance(BigDecimal.ONE)
                .build();
        Customer customer = new Customer();
        customer.setId(123L);
        // when
        Account actual = mapper.map(customer, createAccountRequest);
        // then
        assertThat(actual).isEqualToIgnoringGivenFields(createAccountRequest,"id", "customer", "customerId", "balance");
    }
}