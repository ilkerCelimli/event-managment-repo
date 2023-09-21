package com.portifolyo.paymentservice.services;

import org.portifolyo.requests.paymentservice.PaymentRequest;

import java.sql.SQLException;

public interface PaymentService {

    void handlePaymentRequest(PaymentRequest paymentRequest) throws SQLException;
}
