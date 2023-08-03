package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.Adress;
import com.portifolyo.organizercompanyservice.entity.Company;
import org.portifolyo.requests.organizercompanyservice.AdressRequest;

public interface AdressService extends BaseService<Adress> {
    Adress handleAdressRequest(AdressRequest adressRequest, Company company);
}
