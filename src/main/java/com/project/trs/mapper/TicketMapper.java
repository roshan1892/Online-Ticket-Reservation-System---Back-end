package com.project.trs.mapper;

import com.project.trs.model.Ticket;
import com.project.trs.response.CreateTicketResponse;
import com.project.trs.response.ListTicketResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface TicketMapper {

  @Named("uuidToString")
  static String UUIDToSting(UUID id) {
    if (id == null) {
      return null;
    }

    return id.toString();
  }

  @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
  @Mapping(source = "price", target = "price", qualifiedByName = "convertAmountAfterFetch")
  CreateTicketResponse toResponse(Ticket ticket);

  default BigDecimal convertAmountBeforeSave(BigDecimal ticketPrice) {
    //multiplying decimal price value by 100 as big int can't hold decimal points and decimals is considered only up to 2 points
    return ticketPrice.multiply(BigDecimal.valueOf(100));
  }

  @Named("convertAmountAfterFetch")
  default BigDecimal convertAmountAfterFetch(BigDecimal ticketPrice) {
    //dividing price by 100 as it was saved by multiplying 100 to have decimal points in DB
    return ticketPrice.divide(BigDecimal.valueOf(100));
  }

  default ListTicketResponse getAllTicketResponse(List<Ticket> ticketList) {
    ticketList.forEach(ticket -> {
      if (ticket.getPayment() != null) {
        ticket.getPayment().setTicket(null);
        ticket.getPayment().setAmount(convertAmountAfterFetch(ticket.getPayment().getAmount()));
      }
      ticket.setPrice(convertAmountAfterFetch(ticket.getPrice()));
    });

    return ListTicketResponse.builder()
            .ticketList(ticketList)
            .build();
  }
}
