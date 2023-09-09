package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.CompanyEmailAdress;
import org.portifolyo.requests.organizercompanyservice.CompanyMailAdressRequest;

public interface CompanyEmailService extends BaseService<CompanyEmailAdress> {

    CompanyEmailAdress findByCompanyEmailAdress_CompanyId(String id);
    CompanyEmailAdress handleSaveRequest(CompanyEmailAdress request);

    CompanyEmailAdress handleUpdateRequest(CompanyEmailAdress request);

    void handleDeleteCompanyEmailAdresses(String id);

}
