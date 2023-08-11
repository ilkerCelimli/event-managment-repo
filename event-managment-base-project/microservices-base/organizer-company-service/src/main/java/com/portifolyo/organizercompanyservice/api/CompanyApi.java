package com.portifolyo.organizercompanyservice.api;

import com.portifolyo.organizercompanyservice.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/company")
@RestController
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<Void> saveCompany(@RequestBody SaveOrganizerCompanyRequest request){
        this.companyService.handleSaveRequest(request);
        return ResponseEntity.ok().build();
    }

}
