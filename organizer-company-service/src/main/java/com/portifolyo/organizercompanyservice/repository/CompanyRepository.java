package com.portifolyo.organizercompanyservice.repository;

import com.portifolyo.organizercompanyservice.entity.Company;
import com.portifolyo.organizercompanyservice.repository.projections.CompanyInfo;

import java.util.List;
import java.util.Optional;

public interface CompanyRepository extends BaseRepository<Company>{

    Optional<CompanyInfo> findCompanyById(String id);

    List<CompanyInfo> findCompaniesByActiveTrue();
}
