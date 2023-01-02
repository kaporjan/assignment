package com.an.assignment.web;

import com.an.assignment.IntegrationTest;
import com.an.assignment.domain.CustomerDetails;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class CustomerControllerTest extends IntegrationTest {
    private static final ObjectMapper OM = new ObjectMapper();

    @Test
    void getCustomerDetails() throws Exception {
        mvc.perform((get("/customer/{customerId}", 1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Joan")));
    }

    @Test
    void getNonExistingCustomer() throws Exception {
        mvc.perform((get("/customer/{customerId}", 6666)))
                .andExpect(status().isNotFound());
    }

    @Test
    void createCustomer() throws Exception {
        MvcResult mvcResult = mvc.perform(
                        post("/customer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"name\":\"myName\",\"surname\":\"mySurname\",\"balance\": 11}")
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id", greaterThan(99)))
                .andReturn();
        CustomerDetails customerDetails = OM.readValue(mvcResult.getResponse().getContentAsString(), CustomerDetails.class);

        mvc.perform((get("/customer/{customerId}/account/{accountId}",customerDetails.id(),
                        customerDetails.id()+1)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", equalTo("Default account")))
                .andExpect(jsonPath("$.balance", equalTo(11)));
    }

    @Test
    void missingName() throws Exception {
        mvc.perform(
                        post("/customer")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("{\"surName\":\"\"}")
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    void deleteCustomer() throws Exception {
        mvc.perform(
                        delete("/customer/{customerId}", 1)
                )
                .andExpect(status().isOk());
    }

    @Test
    void deleteNonExistingCustomer() throws Exception {
        mvc.perform(
                        delete("/customer/{customerId}", 9876)
                )
                .andExpect(status().isNotFound());
    }
}
