package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.Company;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;

public interface CompanyService extends BaseService<Company> {

    void handleSaveRequest(SaveOrganizerCompanyRequest request);

}