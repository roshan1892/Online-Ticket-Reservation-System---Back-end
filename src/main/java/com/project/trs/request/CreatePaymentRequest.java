package com.project.trs.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePaymentRequest {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal amount;
    private Instant createdAt;
    private String createdBy;
    private String ticketId;
}
