package com.project.trs.mapper;

import com.project.trs.model.Payment;
import com.project.trs.response.CreatePaymentResponse;
import com.project.trs.response.ListPaymentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PaymentMapper {

  @Named("uuidToString")
  static String UUIDToSting(UUID id) {
    if (id == null) {
      return null;
    }

    return id.toString();
  }

  default BigDecimal convertAmountBeforeSave(BigDecimal paymentAmount) {
    //multiplying decimal price value by 100 as big int can't hold decimal points and decimals is considered only up to 2 points
    return paymentAmount.multiply(BigDecimal.valueOf(100));
  }

  @Named("convertAmountAfterFetch")
  default BigDecimal convertAmountAfterFetch(BigDecimal paymentAmount) {
    //dividing price by 100 as it was saved by multiplying 100 to have decimal points in DB
    return paymentAmount.divide(BigDecimal.valueOf(100));
  }


  @Mapping(source = "id", target = "id", qualifiedByName = "uuidToString")
  @Mapping(source = "amount", target = "amount", qualifiedByName = "convertAmountAfterFetch")
  CreatePaymentResponse toResponse(Payment payment);

  default ListPaymentResponse getAllPaymentResponse(List<Payment> paymentList) {
    paymentList.forEach(payment -> {
      payment.setAmount(convertAmountAfterFetch(payment.getAmount()));
      payment.setTicket(null);
    });
    return ListPaymentResponse.builder()
            .paymentList(paymentList)
            .build();
  }
}
