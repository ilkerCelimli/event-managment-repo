package com.portifolyo.paymentservice.services;

import com.portifolyo.paymentservice.entity.Payment;
import com.portifolyo.paymentservice.repository.PaymentInfo;
import com.portifolyo.paymentservice.util.PaymentResponseMapper;
import org.portifolyo.requests.paymentservice.PaymentRequest;
import org.portifolyo.response.PaymentResponse;

import java.sql.SQLException;
import java.util.List;

public interface PaymentService {

    PaymentResponse handlePaymentRequest(PaymentRequest paymentRequest);
    PaymentResponse updatePayment(PaymentRequest paymentRequest, String id);
    Payment findById(String id);

    void deletePayment(String id);

    List<PaymentInfo> findPaymentsByCompanyId(String id);
}
