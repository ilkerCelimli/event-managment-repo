package com.portifolyo.organizercompanyservice.api;

import com.portifolyo.organizercompanyservice.entity.CompanyEmailAdress;
import com.portifolyo.organizercompanyservice.service.CompanyEmailService;
import com.portifolyo.organizercompanyservice.util.CompanyEmailAdressDtoMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.organizercompanyservice.CompanyMailAdressRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/company-service/email")
@RestController
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")

public class CompanyEmailAdressApi {

    private final CompanyEmailService companyEmailService;

    @PostMapping
    public ResponseEntity<GenericResponse<CompanyEmailAdress>> save(@RequestBody CompanyMailAdressRequest request) {
        return ResponseEntity.ok(
                GenericResponse.SUCCESS(
                        this.companyEmailService.handleSaveRequest(
                                CompanyEmailAdressDtoMapper.companyEmailAdress(request)
                        )
                )
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> delete(@PathVariable String id) {
        this.companyEmailService.handleDeleteCompanyEmailAdresses(id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }


}
