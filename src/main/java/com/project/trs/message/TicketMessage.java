package com.project.trs.message;

import org.springframework.stereotype.Component;

@Component
public class TicketMessage {
    public static final String NULL_TICKET = "The provided ticket doesn't exist.";
    public static final String TICKET_NOT_FOUND = "The provided ticket with id %s doesn't exist.";

}
