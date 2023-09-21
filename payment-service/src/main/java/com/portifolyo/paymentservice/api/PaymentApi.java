package com.portifolyo.paymentservice.api;

import com.portifolyo.paymentservice.repository.PaymentInfo;
import com.portifolyo.paymentservice.services.PaymentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.paymentservice.PaymentRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.PaymentResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/payment-service")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")

public class PaymentApi {

    private final PaymentService paymentService;

    @GetMapping("/find-by-company-id/{id}")
    public ResponseEntity<GenericResponse<List<PaymentInfo>>> findPaymentByCompanyId(@PathVariable String id){
       return ResponseEntity.ok(
               GenericResponse.SUCCESS(paymentService.findPaymentsByCompanyId(id))
       );
    }

    @PostMapping
    public ResponseEntity<GenericResponse<PaymentResponse>> save(@RequestBody PaymentRequest paymentRequest){
        PaymentResponse ref = this.paymentService.handlePaymentRequest(paymentRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS(ref));
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<PaymentResponse>> update(@RequestBody PaymentRequest paymentRequest,@PathVariable String id){
        PaymentResponse ref = this.paymentService.updatePayment(paymentRequest,id);
        return ResponseEntity.ok(GenericResponse.SUCCESS(ref));
    }

}
