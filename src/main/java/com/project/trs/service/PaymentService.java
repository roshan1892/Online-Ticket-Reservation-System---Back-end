package com.project.trs.service;

import com.project.trs.request.CreatePaymentRequest;
import com.project.trs.response.CreatePaymentResponse;
import com.project.trs.response.ListPaymentResponse;

public interface PaymentService {

  CreatePaymentResponse createPayment(CreatePaymentRequest createPaymentRequest);

  ListPaymentResponse listPayments();

  ListPaymentResponse getPaymentByTicketId(String ticketId);
}
