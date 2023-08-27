package com.portifolyo.organizercompanyservice.service.impl;

import com.fasterxml.jackson.core.JsonToken;
import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.exception.GenericException;
import com.portifolyo.organizercompanyservice.feign.UserFeign;
import com.portifolyo.organizercompanyservice.repository.CompanyRepository;
import com.portifolyo.organizercompanyservice.service.AdressService;
import com.portifolyo.organizercompanyservice.service.CompanyService;
import com.portifolyo.organizercompanyservice.util.SaveOrganizerCompanyRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.UserInfo;
import org.portifolyo.utils.JsonTokenUtils;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Slf4j
public class CompanyServiceImpl extends BaseServiceImpl<Company> implements CompanyService {


    private final CompanyRepository companyRepository;
    private final UserFeign userFeign;
    private final AdressService adressService;

    public CompanyServiceImpl(CompanyRepository companyRepository, UserFeign userFeign, AdressService adressService) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.userFeign = userFeign;
        this.adressService = adressService;
    }

    @Override
    @Transactional
    public void handleSaveRequest(SaveOrganizerCompanyRequest request) {
        ResponseEntity<GenericResponse<UserInfo>> response = this.userFeign.registerUser(request.userRegisterRequest());
        if (response.getStatusCode().is2xxSuccessful()
                && response.getBody() != null
                && response.getBody().getData() != null) {
            UserInfo userInfo = response.getBody().getData();
            Company company = SaveOrganizerCompanyRequestMapper.toEntity(request);
            company.setCompanySuperAdminUserId(userInfo.id());
            company.setUpdatedDate(LocalDateTime.now());
            company.setCreatedDate(LocalDateTime.now());
            try {
                company = this.companyRepository.save(company);
                this.adressService.handleAdressRequest(request.adressRequest(), company);
                return;

            } catch (Exception e) {
                String[] arr = new String[]{"ROLE_USER"};
                String token = JsonTokenUtils.generate(request.userRegisterRequest().email(),arr,null);
                this.userFeign.deleteUser(token, request.userRegisterRequest().email());
                throw new GenericException("Company not saved");

            }
        }
        throw new GenericException("Company not saved");
    }

    @Override
    public void inActiveCompany(String id,String token) {
        Company company = findById(id);
        company.setActive(false);

        try {
            this.userFeign.deleteById(token, company.getCompanySuperAdminUserId());

        }
        catch (Exception e){
            log.error(e.getMessage());
            company.setActive(true);
            System.out.println(e.getMessage());
           // throw new GenericException("User-service Authentication Error");
        }
        finally {
        this.companyRepository.save(company);
        }
    }

    @Override
    @Transactional
    public void updateCompany(SaveOrganizerCompanyRequest request, String id) {
        UpdateHelper<SaveOrganizerCompanyRequest,Company> helper = new UpdateHelper<>();
        Company company = findById(id);
        company = helper.updateHelper(request,company);
        company.setUpdatedDate(LocalDateTime.now());
        update(company);
    }

}
