package com.project.trs.service.impl;

import com.project.trs.exception.InvalidInputException;
import com.project.trs.mapper.PaymentMapper;
import com.project.trs.message.PaymentMessage;
import com.project.trs.message.TicketMessage;
import com.project.trs.model.Payment;
import com.project.trs.model.Ticket;
import com.project.trs.repository.PaymentRepository;
import com.project.trs.request.CreatePaymentRequest;
import com.project.trs.response.CreatePaymentResponse;
import com.project.trs.response.ListPaymentResponse;
import com.project.trs.service.PaymentService;
import com.project.trs.service.TicketService;
import com.project.trs.validation.PaymentValidator;
import com.project.trs.validation.TicketValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentServiceImpl implements PaymentService {

  private final PaymentRepository paymentRepository;
  private final PaymentMapper paymentMapper;
  private final TicketService ticketService;
  private final TicketValidator ticketValidator;

  @Override
  public CreatePaymentResponse createPayment(CreatePaymentRequest request) {
    Ticket ticket = ticketService.getTicket(request.getTicketId());
    Optional<Payment> paymentFromDBOptional = paymentRepository.findByTicket(ticket);
    log.info("Validating request.");
    ticketValidator.throwIfTicketIsNull(ticket);
    PaymentValidator.throwIfPaymentAmountIsNotEqualToPaymentPrice(ticket.getPrice(), request.getAmount());
    PaymentValidator.throwIfPaymentIsAlreadyMade(paymentFromDBOptional);
    log.info("Validation successful.");

    Payment payment = Payment.builder()
            .amount(paymentMapper.convertAmountBeforeSave(request.getAmount()))
            .createdAt(Instant.now())
            .createdBy(request.getCreatedBy())
            .description(request.getDescription())
            .name(request.getName())
            .ticket(ticket)
            .build();
    Payment savedPayment = paymentRepository.save(payment);

    ticket.setPaymentStatus(true);
    ticketService.createTicket(ticket);

    return paymentMapper.toResponse(savedPayment);
  }

  @Override
  public ListPaymentResponse listPayments() {
    List<Payment> payments = paymentRepository.findAll();
    return paymentMapper.getAllPaymentResponse(payments);
  }

  @Override
  public ListPaymentResponse getPaymentByTicketId(String ticketId) {
    Ticket ticket = ticketService.getTicket(ticketId);
    Payment payment = paymentRepository.findByTicket(ticket)
            .orElseThrow(() -> new InvalidInputException(PaymentMessage.NO_PAYMENT_ADDED_FOR_THIS_TICKET));

    return paymentMapper.getAllPaymentResponse(Arrays.asList(payment));
  }
}
