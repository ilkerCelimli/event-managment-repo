package com.portifolyo.paymentservice.services.impl;

import com.portifolyo.paymentservice.entity.Payment;
import com.portifolyo.paymentservice.exception.CustomSqlException;
import com.portifolyo.paymentservice.exception.NotFoundException;
import com.portifolyo.paymentservice.feign.CompanyServiceFeignClient;
import com.portifolyo.paymentservice.repository.PaymentInfo;
import com.portifolyo.paymentservice.repository.PaymentRepository;
import com.portifolyo.paymentservice.services.PaymentService;
import com.portifolyo.paymentservice.util.PaymentRequestMapper;
import com.portifolyo.paymentservice.util.PaymentResponseMapper;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.paymentservice.PaymentRequest;
import org.portifolyo.response.CompanyResponse;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.PaymentResponse;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final CompanyServiceFeignClient companyServiceFeignClient;

    @Override
    public PaymentResponse handlePaymentRequest(PaymentRequest paymentRequest) {
        ResponseEntity<GenericResponse<CompanyResponse>> response = this.companyServiceFeignClient.findCompanyById(paymentRequest.companyId());
        if (response.getStatusCode().is2xxSuccessful() && (response.getBody() != null) && (response.getBody().getData() != null)) {
            Payment ref = PaymentRequestMapper.toEntity(paymentRequest);
            CompanyResponse companyResponse = response.getBody().getData();
            ref.setCommuisionRate(BigDecimal.valueOf(companyResponse.commusionRate()));
            ref = this.paymentRepository.save(ref);
            if (ref.getId() == null) {
                throw new CustomSqlException();
            }
            return PaymentResponseMapper.toDto(ref);
        }
        throw new NotFoundException(paymentRequest.companyId());
    }

    @Override
    public PaymentResponse updatePayment(PaymentRequest paymentRequest, String id) {
        UpdateHelper<PaymentRequest,Payment> helper = new UpdateHelper<>();
        Payment ref = findById(id);
        ref = helper.updateHelper(paymentRequest,ref);
        ref = this.paymentRepository.save(ref);
        return PaymentResponseMapper.toDto(ref);
    }

    @Override
    public Payment findById(String id) {
        return this.paymentRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void deletePayment(String id) {
        Payment ref = findById(id);
        ref.setIsDeleted(true);
        this.paymentRepository.save(ref);
    }

    @Override
    public List<PaymentInfo> findPaymentsByCompanyId(String id) {
        return this.paymentRepository.findPaymentsByPaidCompanyId(id);
    }
}
