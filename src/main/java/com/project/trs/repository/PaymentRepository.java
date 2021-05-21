package com.project.trs.repository;

import com.project.trs.model.Payment;
import com.project.trs.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {
  long countByCreatedBy(String user);

  Optional<Payment> findByTicket(Ticket ticket);
}
