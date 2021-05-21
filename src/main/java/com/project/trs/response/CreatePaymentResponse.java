package com.project.trs.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePaymentResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal amount;
    private String createdAt;
    private String createdBy;
}
