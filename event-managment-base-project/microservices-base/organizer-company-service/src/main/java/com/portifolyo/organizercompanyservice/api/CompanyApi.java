package com.portifolyo.organizercompanyservice.api;

import com.portifolyo.organizercompanyservice.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/company")
@RestController
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping("/")
    public ResponseEntity<GenericResponse<Void>> saveCompany(@RequestBody SaveOrganizerCompanyRequest request){
        this.companyService.handleSaveRequest(request);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @DeleteMapping("/delete-company/{id}")
    public ResponseEntity<GenericResponse<Void>> deleteCompany(@PathVariable String id, HttpServletRequest request){
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        this.companyService.inActiveCompany(id,token);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

}
