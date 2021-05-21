package com.project.trs.validation;

import com.project.trs.exception.InvalidInputException;
import com.project.trs.message.PaymentMessage;
import com.project.trs.model.Payment;
import com.project.trs.model.Ticket;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;

@Component
public class PaymentValidator {

  public static void throwIfPaymentAmountIsNotEqualToPaymentPrice(BigDecimal ticketPrice, BigDecimal paymentAmount) {
    if (ticketPrice.compareTo(paymentAmount) != 0) {
      throw new InvalidInputException(String.format(PaymentMessage.INVALID_PAYMENT_AMOUNT, ticketPrice, paymentAmount));
    }
  }

  public static void throwIfPaymentIsAlreadyMade(Optional<Payment> paymentFromDBOptional) {
    if (paymentFromDBOptional.isPresent()) {
      throw new InvalidInputException(String.format(PaymentMessage.PAYMENT_ALREADY_MADE));
    }
  }
}
