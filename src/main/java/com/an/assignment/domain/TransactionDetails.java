package com.an.assignment.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record TransactionDetails(Long id, Long customerId, Long accountId, BigDecimal change) {

}
