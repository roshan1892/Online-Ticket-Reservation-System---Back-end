package com.project.trs.controller;

import com.project.trs.form.CreateTicketForm;
import com.project.trs.model.Ticket;
import com.project.trs.response.CreateTicketResponse;
import com.project.trs.response.ListTicketResponse;
import com.project.trs.service.TicketService;
import com.project.trs.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.Instant;

@RestController
@AllArgsConstructor
@Slf4j
public class TicketController {
    private TicketService ticketService;

    @PostMapping("/createTicket")
    @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
    ResponseEntity<CreateTicketResponse> createSchedule(@Valid @RequestBody CreateTicketForm createTicketForm, Authentication authentication) {

        log.info("Creating Ticket.");
        log.debug("{}", createTicketForm);

        CreateTicketResponse response = ticketService.createTicket(Ticket.builder()
                .name(createTicketForm.getName())
                .description(createTicketForm.getDescription())
                .price(createTicketForm.getPrice())
                .createdAt(Instant.now())
                .createdBy(authentication.getName())
                .build());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/listTickets")
    @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
    ResponseEntity<ListTicketResponse> createSchedule() {

        log.info("fetching all tickets.");

        ListTicketResponse response = ticketService.listTickets();
        return ResponseEntity.ok(response);
    }
}
