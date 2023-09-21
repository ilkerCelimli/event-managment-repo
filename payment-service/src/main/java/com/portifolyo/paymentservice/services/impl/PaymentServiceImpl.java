package com.portifolyo.paymentservice.services.impl;

import com.portifolyo.paymentservice.entity.Payment;
import com.portifolyo.paymentservice.exception.CustomSqlException;
import com.portifolyo.paymentservice.feign.CompanyServiceFeignClient;
import com.portifolyo.paymentservice.repository.PaymentRepository;
import com.portifolyo.paymentservice.services.PaymentService;
import com.portifolyo.paymentservice.util.PaymentRequestMapper;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.paymentservice.PaymentRequest;
import org.portifolyo.response.CompanyResponse;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.SQLException;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CompanyServiceFeignClient companyServiceFeignClient;

    @Override
    public void handlePaymentRequest(PaymentRequest paymentRequest) {
        ResponseEntity<GenericResponse<CompanyResponse>> response = this.companyServiceFeignClient.findCompanyById(paymentRequest.companyId());
        if (response.getStatusCode().is2xxSuccessful() && (response.getBody() != null) && (response.getBody().getData() != null)) {
            Payment ref = PaymentRequestMapper.toEntity(paymentRequest);
            CompanyResponse companyResponse = response.getBody().getData();
            ref.setCommuisionRate(BigDecimal.valueOf(companyResponse.commusionRate()));
            ref = this.paymentRepository.save(ref);
            if (ref.getId() == null) {
                throw new CustomSqlException();
            }
        }
    }
}
