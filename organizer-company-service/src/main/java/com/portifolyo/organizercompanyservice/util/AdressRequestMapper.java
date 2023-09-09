package com.portifolyo.organizercompanyservice.util;

import com.portifolyo.organizercompanyservice.entity.Adress;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.portifolyo.requests.organizercompanyservice.AdressRequest;
@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class AdressRequestMapper {
    public static Adress toEntity(AdressRequest adressRequest){
        Adress entity = new Adress();
        entity.setOpenAdress(adressRequest.openAdress());
        entity.setActive(true);
        return entity;
    }

}
