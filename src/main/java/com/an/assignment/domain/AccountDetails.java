package com.an.assignment.domain;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record AccountDetails(Long id, Long customerId, String name, BigDecimal balance) {

}
