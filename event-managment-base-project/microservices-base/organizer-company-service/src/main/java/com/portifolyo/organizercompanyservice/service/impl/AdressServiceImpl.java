package com.portifolyo.organizercompanyservice.service.impl;

import com.portifolyo.organizercompanyservice.entity.Adress;
import com.portifolyo.organizercompanyservice.entity.City;
import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.exception.GenericException;
import com.portifolyo.organizercompanyservice.repository.AdressRepository;
import com.portifolyo.organizercompanyservice.repository.CityRepository;
import com.portifolyo.organizercompanyservice.service.AdressService;
import org.portifolyo.requests.organizercompanyservice.AdressRequest;

public class AdressServiceImpl extends BaseServiceImpl<Adress> implements AdressService {

    private final AdressRepository adressRepository;
    private final CityRepository cityRepository;
    public AdressServiceImpl(AdressRepository adressRepository, CityRepository cityRepository) {
        super(adressRepository);
        this.adressRepository = adressRepository;
        this.cityRepository = cityRepository;
    }


    public Adress handleAdressRequest(AdressRequest adressRequest, Company company){
        City c = this.cityRepository.findById(adressRequest.cityId()).orElseThrow(() -> new GenericException("City Not found"));
        Adress adress = new Adress();
        adress.setCity(c);
        adress.setOpenAdress(adressRequest.openAdress());
        adress.setCompany(company);
        return save(adress);
    }

}
