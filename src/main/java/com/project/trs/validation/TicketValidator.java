package com.project.trs.validation;

import com.project.trs.exception.InvalidInputException;
import com.project.trs.message.TicketMessage;
import com.project.trs.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketValidator {
  public static void throwIfTicketIsNull(Ticket ticket) {
    if (ticket == null) {
      throw new InvalidInputException(TicketMessage.NULL_TICKET);
    }
  }
}
