package com.portifolyo.paymentservice.util;

import com.portifolyo.paymentservice.entity.Payment;
import org.portifolyo.requests.paymentservice.PaymentRequest;

public class PaymentRequestMapper {

    private PaymentRequestMapper(){}

    public static Payment toEntity(PaymentRequest paymentRequest){
        return new Payment(null,
                paymentRequest.name(),
                paymentRequest.surname(),
                paymentRequest.cardFirstSixNumber(),
                paymentRequest.cardLastSixNumber(),
                paymentRequest.total(),
                paymentRequest.userId(),
                paymentRequest.companyId(),
                null,null);
    }

}
