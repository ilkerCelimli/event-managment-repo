package com.portifolyo.organizercompanyservice.service.impl;

import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.exception.GenericException;
import com.portifolyo.organizercompanyservice.exception.NotFoundException;
import com.portifolyo.organizercompanyservice.feign.UserFeign;
import com.portifolyo.organizercompanyservice.repository.CompanyRepository;
import com.portifolyo.organizercompanyservice.repository.projections.CompanyInfo;
import com.portifolyo.organizercompanyservice.service.AdressService;
import com.portifolyo.organizercompanyservice.service.CompanyService;
import com.portifolyo.organizercompanyservice.util.CompanyResponseMapper;
import com.portifolyo.organizercompanyservice.util.SaveOrganizerCompanyRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.organizercompanyservice.SaveOrganizerCompanyRequest;
import org.portifolyo.requests.userservice.UserLoginRequest;
import org.portifolyo.response.CompanyResponse;
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
        if (response != null && response.getBody() != null
                && response.getBody().getData() != null && response.getStatusCode().is2xxSuccessful()
        ) {
            UserInfo userInfo = response.getBody().getData();
            Company company = SaveOrganizerCompanyRequestMapper.toEntity(request);
            company.setCompanySuperAdminUserId(userInfo.id());
            try {
                company = super.save(company);
                this.adressService.handleAdressRequest(request.adressRequest(), company);
                return;

            } catch (Exception e) {
                String[] arr = new String[]{"ROLE_USER"};
                this.userFeign.deleteUser(request.userRegisterRequest().email());
                throw new GenericException("Company not saved");
            }
        }
        throw new GenericException("Company not saved");
    }

    @Override
    public void inActiveCompany(String id, String token) {
        Company company = findById(id);
        company.setActive(false);
        try {
            this.userFeign.deleteById(company.getCompanySuperAdminUserId());
            this.companyRepository.save(company);
        } catch (Exception e) {
            log.error(e.getMessage());
            company.setActive(true);
            this.companyRepository.save(company);
            throw new GenericException("User-service Authentication Error");
        }
    }

    @Override
    public CompanyResponse findCompanyById(String id) {
        return CompanyResponseMapper.toDto(
                findById(id)
        );
    }

    @Override
    @Transactional
    public void updateCompany(SaveOrganizerCompanyRequest request, String id) {
        UpdateHelper<SaveOrganizerCompanyRequest, Company> helper = new UpdateHelper<>();
        Company company = findById(id);
        company = helper.updateHelper(request, company);
        company.setUpdatedDate(LocalDateTime.now());
        update(company);
    }

}
