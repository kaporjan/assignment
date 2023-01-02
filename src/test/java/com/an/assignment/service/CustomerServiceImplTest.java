package com.an.assignment.service;

import com.an.assignment.domain.CreateAccountRequest;
import com.an.assignment.domain.CreateCustomerRequest;
import com.an.assignment.domain.CustomerDetails;
import com.an.assignment.entity.Customer;
import com.an.assignment.exception.NotFoundException;
import com.an.assignment.mapper.CustomerMapper;
import com.an.assignment.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {
    @InjectMocks
    private CustomerServiceImpl service;
    @Mock
    private CustomerRepository customerRepositoryMock;
    @Mock
    private CustomerMapper customerMapperMock;
    @Mock
    private AccountService accountService;
    @Mock
    private Customer customerMock;
    private final CustomerDetails customerDetails = CustomerDetails.builder()
            .id(null)
            .name("name")
            .surname("surname")
            .build();


    @BeforeEach
    void setup() {
        lenient().when(customerMock.getName()).thenReturn("name");
        lenient().when(customerMock.getSurname()).thenReturn("surname");

        lenient().when(customerRepositoryMock.findById(any())).thenReturn(Optional.of(customerMock));
    }

    @Test
    void canCreateCustomer() {
        // given
        when(customerMapperMock.map(any(CreateCustomerRequest.class)))
                .thenReturn(customerMock);
        when(customerRepositoryMock.save(any())).thenReturn(customerMock);
        when(customerMapperMock.map(any(Customer.class))).thenReturn(customerDetails);
        CreateCustomerRequest request = CreateCustomerRequest.builder()
                .name("name")
                .surname("surname")
                .balance(BigDecimal.ONE)
                .build();
        CreateAccountRequest createAccountReq = CreateAccountRequest.builder()
                .customerId(0L)
                .name("Default account")
                .balance(BigDecimal.ONE)
                .build();

        // when
        CustomerDetails actual = service.createCustomer(request);

        // then
        verify(customerMapperMock).map(eq(request));
        verify(customerRepositoryMock).save(eq(customerMock));
        verify(customerMapperMock).map(eq(customerMock));
        verify(accountService).createAccount(eq(createAccountReq));

        assertThat(actual).isEqualTo(customerDetails);
    }

    @Test
    void canGetCustomer() {
        // given
        when(customerRepositoryMock.findById(any())).thenReturn(Optional.of(customerMock));
        when(customerMapperMock.map(any(Customer.class))).thenReturn(customerDetails);
        // when
        CustomerDetails actual = service.getCustomer(1L);

        // then
        verify(customerRepositoryMock).findById(eq(1L));
        assertThat(actual).isEqualTo(customerDetails);
    }

    @Test
    void canGetCustomerDetailsIfNotFoundThrowsException() {
        // given
        when(customerRepositoryMock.findById(any())).thenReturn(Optional.empty());

        // when
        assertThatExceptionOfType(NotFoundException.class)
                .isThrownBy(() -> service.getCustomer(1L));

        // then
        verify(customerRepositoryMock).findById(1L);
    }

    @Test
    void canDeleteCustomer() {
        // given

        // when
        service.deleteCustomer(1L);

        // then
        verify(customerRepositoryMock).deleteById(eq(1L));
    }
}