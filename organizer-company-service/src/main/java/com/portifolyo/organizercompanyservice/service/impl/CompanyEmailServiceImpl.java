package com.portifolyo.organizercompanyservice.service.impl;

import com.portifolyo.organizercompanyservice.entity.CompanyEmailAdress;
import com.portifolyo.organizercompanyservice.exception.NotFoundException;
import com.portifolyo.organizercompanyservice.repository.CompanyEmailServiceRepository;
import com.portifolyo.organizercompanyservice.service.CompanyEmailService;
import org.portifolyo.requests.organizercompanyservice.CompanyMailAdressRequest;
import org.springframework.stereotype.Service;

@Service
public class CompanyEmailServiceImpl extends BaseServiceImpl<CompanyEmailAdress> implements CompanyEmailService {
    private final CompanyEmailServiceRepository repository;
    public CompanyEmailServiceImpl(CompanyEmailServiceRepository baseRepository) {
        super(baseRepository);
        this.repository = baseRepository;
    }

    @Override
    public CompanyEmailAdress findByCompanyEmailAdress_CompanyId(String id) {
        return this.repository.findByCompanyId(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public CompanyEmailAdress handleSaveRequest(CompanyEmailAdress request) {
       CompanyEmailAdress ref =  super.save(request);
       return ref;
    }

    @Override
    public CompanyEmailAdress handleUpdateRequest(CompanyEmailAdress request) {
        return super.update(request);
    }

    @Override
    public void handleDeleteCompanyEmailAdresses(String id) {
       CompanyEmailAdress ref = this.findByCompanyEmailAdress_CompanyId(id);
       this.repository.delete(ref);
    }
}
