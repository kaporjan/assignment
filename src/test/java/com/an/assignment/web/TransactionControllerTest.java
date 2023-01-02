package com.an.assignment.web;

import com.an.assignment.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Stream.generate;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TransactionControllerTest extends IntegrationTest {
    private static final String TOO_LONG = generate(() -> "x").limit(129).collect(joining());

    @Test
    void listTransactionsOfAccount() throws Exception {
        mvc.perform((get("/customer/{customerId}/account/{accountId}/transaction", 1,4)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[*]", hasSize(1)));
    }

    @Test
    void createTransaction() throws Exception {
        mvc.perform(
                post("/customer/{customerId}/account/{accountId}/transaction",11,1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("        {\n" +
                                "            \"customerId\": 1,\n" +
                                "            \"accountId\": 4,\n" +
                                "            \"change\": 13\n" +
                                "        }")
        )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(0)));
    }

}
