package com.portifolyo.organizercompanyservice.util;

import com.portifolyo.organizercompanyservice.entity.Company;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
public class SaveOrganizerCompanyRequestMapper {


    public static Company toEntity(SaveOrganizerCompanyRequest request){
        Company company = new Company();
        company.setTaxNumber(request.taxNumber());
        company.setCompanyName(request.companyName());
        company.setPhoneNumber(request.phoneNumber());
        return company;
    }

}
