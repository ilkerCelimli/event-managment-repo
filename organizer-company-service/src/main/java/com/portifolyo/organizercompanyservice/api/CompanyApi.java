package com.portifolyo.organizercompanyservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.service.CompanyService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.apache.http.HttpHeaders;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/company-service/company")
@RestController
@RequiredArgsConstructor
public class CompanyApi {

    private final CompanyService companyService;

    @PostMapping
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

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> updateCompany(@PathVariable("id") String id, @RequestBody @JsonInclude(JsonInclude.Include.NON_NULL) SaveOrganizerCompanyRequest request ){
        this.companyService.updateCompany(request,id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping
    public ResponseEntity<GenericResponse<List<Company>>> findAll(){
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.companyService.findAll()));
    }

}
