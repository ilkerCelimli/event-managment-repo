package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.Company;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.portifolyo.response.CompanyResponse;

public interface CompanyService extends BaseService<Company> {

    void handleSaveRequest(SaveOrganizerCompanyRequest request);
    void inActiveCompany(String organizerId,String token);
    CompanyResponse findCompanyById(String id);
    void updateCompany(SaveOrganizerCompanyRequest request,String id);

}
