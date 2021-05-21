package com.project.trs.message;

public class PaymentMessage {
  public static final String INVALID_PAYMENT_AMOUNT = "The payment amount %.2f id different from ticket price %.2f. Please provide a valid payment amount.";
  public static final String PAYMENT_ALREADY_MADE = "The payment for provided ticket is already made.";
  public static final String NO_PAYMENT_ADDED_FOR_THIS_TICKET = "No payment is added for this ticket.";

}
