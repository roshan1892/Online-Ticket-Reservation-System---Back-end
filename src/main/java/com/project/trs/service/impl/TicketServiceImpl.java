package com.project.trs.service.impl;

import com.project.trs.exception.InvalidInputException;
import com.project.trs.mapper.TicketMapper;
import com.project.trs.message.TicketMessage;
import com.project.trs.model.Ticket;
import com.project.trs.repository.TicketRepository;
import com.project.trs.response.CreateTicketResponse;
import com.project.trs.response.ListTicketResponse;
import com.project.trs.service.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketServiceImpl implements TicketService {

  private final TicketRepository ticketRepository;
  private final TicketMapper ticketMapper;

  @Override
  public CreateTicketResponse createTicket(Ticket ticket) {
    ticket.setPrice(ticketMapper.convertAmountBeforeSave(ticket.getPrice()));
    Ticket savedTicket = ticketRepository.save(ticket);
    return ticketMapper.toResponse(savedTicket);
  }

  @Override
  public ListTicketResponse listTickets() {
    List<Ticket> tickets = ticketRepository.findAll();
    return ticketMapper.getAllTicketResponse(tickets);
  }

  @Override
  public Ticket getTicket(String id) {
    Ticket ticket = ticketRepository.findById(UUID.fromString(id))
            .orElseThrow(() -> new InvalidInputException(String.format(TicketMessage.TICKET_NOT_FOUND, id)));
    ticket.setPrice(ticketMapper.convertAmountAfterFetch(ticket.getPrice()));
    return ticket;
  }
}
