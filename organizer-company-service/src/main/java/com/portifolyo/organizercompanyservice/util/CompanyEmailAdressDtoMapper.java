package com.portifolyo.organizercompanyservice.util;

import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.entity.CompanyEmailAdress;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.portifolyo.requests.organizercompanyservice.CompanyMailAdressRequest;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CompanyEmailAdressDtoMapper {

    public static CompanyEmailAdress companyEmailAdress(CompanyMailAdressRequest request){
        CompanyEmailAdress entity = new CompanyEmailAdress(request.host(), request.email(),request.password(),request.port(),request.isTtsRequired(),null);
        Company company = new Company();
        company.setId(request.companyId());
        return entity;
    }

}
