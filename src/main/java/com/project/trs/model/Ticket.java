package com.project.trs.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tickets")
public class Ticket {
    @Id
    @GeneratedValue(generator = "uuid")
    @Column(nullable = false)
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Instant createdAt;
    private String createdBy;
    @OneToOne(mappedBy = "ticket", fetch = FetchType.LAZY)
    private Payment payment;
    private boolean paymentStatus;
}
