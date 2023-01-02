package com.an.assignment.mapper;

import com.an.assignment.domain.CreateCustomerRequest;
import com.an.assignment.entity.Customer;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

public class CustomerMapperTest {
    @Test
    void canMap() {
        // given
        CustomerMapper mapper = new CustomerMapper();
        CreateCustomerRequest createCustomerRequest = CreateCustomerRequest.builder()
                .name("name")
                .surname("surname")
                .balance(BigDecimal.ONE)
                .build();
        // when
        Customer actual = mapper.map(createCustomerRequest);
        // then
        assertThat(actual).isEqualToIgnoringGivenFields(createCustomerRequest,"id");
    }
}