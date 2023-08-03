package com.portifolyo.organizercompanyservice.util;

import com.portifolyo.organizercompanyservice.entity.Adress;
import org.portifolyo.requests.organizercompanyservice.AdressRequest;

public class AdressRequestMapper {
    private AdressRequestMapper() {}
    public static Adress toEntity(AdressRequest adressRequest){
        Adress entity = new Adress();
        entity.setOpenAdress(adressRequest.openAdress());
        entity.setActive(true);
        return entity;
    }

}
