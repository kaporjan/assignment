package com.an.assignment.domain;

import lombok.Builder;

@Builder
public record CustomerDetails(Long id, String name, String surname) {
}
