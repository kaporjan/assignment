package com.an.assignment.domain;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Builder
@Value
public class CreateCustomerRequest {
    @Length(max = 128)
    @NotBlank
    private String name;
    @Length(max = 128)
    @NotBlank
    private String surname;
    @DecimalMin(value = "0.0")
    private BigDecimal balance;
}
