package com.portifolyo.organizercompanyservice.service.impl;

import com.portifolyo.organizercompanyservice.entity.Adress;
import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.entity.Place;
import com.portifolyo.organizercompanyservice.repository.PlaceRepository;
import com.portifolyo.organizercompanyservice.service.AdressService;
import com.portifolyo.organizercompanyservice.service.CompanyService;
import com.portifolyo.organizercompanyservice.service.PlacesService;
import com.portifolyo.organizercompanyservice.util.SavePlaceRequestMapper;
import org.portifolyo.requests.organizercompanyservice.SavePlaceRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlacesServiceImpl extends BaseServiceImpl<Place> implements PlacesService {
    private final PlaceRepository placeRepository;
    private final CompanyService companyService;
    private final AdressService adressService;
    public PlacesServiceImpl(PlaceRepository baseRepository, PlaceRepository placeRepository, CompanyService companyService, AdressService adressService) {
        super(baseRepository);
        this.placeRepository = placeRepository;
        this.companyService = companyService;
        this.adressService = adressService;
    }


    @Override
    @Transactional
    public void handlePlaceRequest(SavePlaceRequest savePlaceRequest) {

        Place p = SavePlaceRequestMapper.toEntity(savePlaceRequest);
        Company c = companyService.findById(savePlaceRequest.companyId());
        p.setCompany(c);
        Adress adress = adressService.handleAdressRequest(savePlaceRequest.adressRequest(),c);
        p.setAdress(adress);
        save(p);
    }

    @Override
    public void updatePlaceBusyStatus(boolean update, String placeId) {
        this.placeRepository.updatePlaceBusyStatus(update,placeId);
    }
}
