package com.portifolyo.paymentservice.feign;

import org.portifolyo.response.CompanyResponse;
import org.portifolyo.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("organizer-company-service")
public interface CompanyServiceFeignClient {

    @GetMapping("/{id}")
    ResponseEntity<GenericResponse<CompanyResponse>> findCompanyById(@PathVariable String id);
}
