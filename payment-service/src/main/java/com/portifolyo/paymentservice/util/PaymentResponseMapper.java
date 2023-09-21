package com.portifolyo.paymentservice.util;

import com.portifolyo.paymentservice.entity.Payment;
import org.portifolyo.response.PaymentResponse;

import java.time.Instant;
import java.util.Date;

public class PaymentResponseMapper {
    private PaymentResponseMapper(){}

    public static PaymentResponse toDto(Payment ref){
        return new PaymentResponse(
                ref.getId(),
                ref.getName(),
                ref.getSurname(),
                Date.from(Instant.now()),
                ref.getTotal().doubleValue()
        );
    }
}
