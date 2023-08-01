package com.portifolyo.organizercompanyservice.service.impl;

import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.feign.UserFeign;
import com.portifolyo.organizercompanyservice.repository.CompanyRepository;
import com.portifolyo.organizercompanyservice.service.CompanyService;
import com.portifolyo.organizercompanyservice.util.SaveOrganizerCompanyRequestMapper;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.UserInfo;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {


    private final CompanyRepository companyRepository;
    private final UserFeign userFeign;

    public CompanyServiceImpl(CompanyRepository companyRepository, UserFeign userFeign) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.userFeign = userFeign;
    }

    @Override
    @Transactional
    //TODO search microservice transaction
    public void handleSaveRequest(SaveOrganizerCompanyRequest request) {
        ResponseEntity<GenericResponse<UserInfo>> response = this.userFeign.registerUser(request.userRegisterRequest());
        if (response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null
                && response.getBody().getData() != null) {
            try {
                UserInfo userInfo = response.getBody().getData();
                Company company = SaveOrganizerCompanyRequestMapper.toEntity(request);
                company.setCompanySuperAdminUserId(userInfo.id());
                this.companyRepository.save(company);

            }

            catch (Exception e){
                // TODO write this.
            }


            return;
        }
        // TODO refactor this expcetion
        throw new RuntimeException();
    }
}
