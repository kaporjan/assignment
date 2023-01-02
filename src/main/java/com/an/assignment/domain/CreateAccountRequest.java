package com.an.assignment.domain;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Value;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;

@Builder
@Value
public class CreateAccountRequest {
    @Nonnull
    private Long customerId;
    @Length(max = 128)
    @NotBlank
    private String name;
    @DecimalMin(value = "0.0")
    private BigDecimal balance;
}
