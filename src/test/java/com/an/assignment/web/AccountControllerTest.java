package com.an.assignment.web;

import com.an.assignment.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class AccountControllerTest extends IntegrationTest {
    private static final String TOO_LONG = generate(() -> "x").limit(129).collect(joining());

    @Test
    void listAccountsOfCustomer() throws Exception {
        mvc.perform((get("/customer/{customerId}/account", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*]", hasSize(2)));
    }

    @Test
    void getAccountDetails() throws Exception {
        mvc.perform((get("/customer/{customerId}/account/{accountId}",1,4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("My first account")))
                .andExpect(jsonPath("$.balance", equalTo(11)));
    }

    @Test
    void getNonExistingCustomerAccount() throws Exception {
        mvc.perform((get("/customer/{customerId}/account/{accountId}",11,1)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createAccount() throws Exception {
        mvc.perform(
                post("/customer/{customerId}/account",11)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": 1 ,\"name\":\"name\",\"balance\": 1 }")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

    @Test
    void nameTooLong() throws Exception {
        mvc.perform(
                post("/customer/{customerId}/account",11)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": 1 ,\"name\":\"" + TOO_LONG + "\",\"balance\": 1 }")
        )
                .andExpect(status().isBadRequest());
    }
    @Test
    void deleteAccount() throws Exception {
        mvc.perform(
                delete("/customer/{customerId}/account/{accountId}",1,4))
                .andExpect(status().isOk());
    }

    @Test
    void deleteNonExistingAccount() throws Exception {
        mvc.perform(
                delete("/customer/{customerId}/account/{accountId}",11,1))
                .andExpect(status().isNotFound());
    }
}
