package com.project.trs.repository;

import com.project.trs.model.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
    long countByCreatedBy(String user);
}
