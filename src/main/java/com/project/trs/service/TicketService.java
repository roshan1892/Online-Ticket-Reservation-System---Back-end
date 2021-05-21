package com.project.trs.service;

import com.project.trs.model.Ticket;
import com.project.trs.response.CreateTicketResponse;
import com.project.trs.response.ListTicketResponse;

public interface TicketService {

    CreateTicketResponse createTicket(Ticket ticket);

    ListTicketResponse listTickets();

    Ticket getTicket(String id);
}
