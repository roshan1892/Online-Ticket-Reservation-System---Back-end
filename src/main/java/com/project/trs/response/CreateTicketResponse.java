package com.project.trs.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateTicketResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String createdAt;
    private String createdBy;
}
