package com.project.trs.controller;

import com.project.trs.form.CreatePaymentForm;
import com.project.trs.request.CreatePaymentRequest;
import com.project.trs.response.CreatePaymentResponse;
import com.project.trs.response.ListPaymentResponse;
import com.project.trs.service.PaymentService;
import com.project.trs.utils.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Slf4j
public class PaymentController {
  private PaymentService paymentService;

  @PostMapping("/createPayment")
  @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
  ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody CreatePaymentForm createPaymentForm,
                                                      Authentication authentication) {

    log.info("Creating Ticket.");
    log.debug("{}", createPaymentForm);

    CreatePaymentResponse response = paymentService.createPayment(CreatePaymentRequest.builder()
            .name(createPaymentForm.getName())
            .description(createPaymentForm.getDescription())
            .amount(createPaymentForm.getAmount())
            .ticketId(createPaymentForm.getTicketId())
            .createdBy(authentication.getName())
            .build());

    return ResponseEntity.ok(response);
  }

  @GetMapping("/listPayments")
  @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
  ResponseEntity<ListPaymentResponse> listPayments() {

    log.info("fetching all payments.");

    ListPaymentResponse response = paymentService.listPayments();
    return ResponseEntity.ok(response);
  }

  @GetMapping("/fetchPayment/{ticketId}")
  @PreAuthorize("hasRole('" + JwtUtils.ROLE_USER + "')")
  ResponseEntity<?> fetchPayment(@PathVariable String ticketId) {

    log.info("fetching payment for ticket {}.", ticketId);

    ListPaymentResponse response = paymentService.getPaymentByTicketId(ticketId);
    return ResponseEntity.ok(response);
  }
}
