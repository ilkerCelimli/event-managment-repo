package com.portifolyo.organizercompanyservice.util;

import com.portifolyo.organizercompanyservice.entity.Company;
import org.portifolyo.response.CompanyResponse;

public class CompanyResponseMapper {

    private CompanyResponseMapper(){}

    public static CompanyResponse toDto(Company company){
        return new CompanyResponse(company.getId(),company.getCompanyName(), company.getTaxNumber(), company.getPhoneNumber(), company.getCompanySuperAdminUserId(),company.getCommusionRate());
    }

}
