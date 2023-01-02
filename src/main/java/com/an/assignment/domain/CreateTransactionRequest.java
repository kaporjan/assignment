package com.an.assignment.domain;

import jakarta.annotation.Nonnull;
import lombok.Builder;
import lombok.Value;

import java.math.BigDecimal;

@Builder
@Value
public class CreateTransactionRequest {
    @Nonnull
    private Long customerId;
    @Nonnull
    private Long accountId;
    @Nonnull
    private BigDecimal change;
}
