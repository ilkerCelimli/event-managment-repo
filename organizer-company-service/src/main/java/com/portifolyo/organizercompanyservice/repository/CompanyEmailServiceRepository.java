package com.portifolyo.organizercompanyservice.repository;

import com.portifolyo.organizercompanyservice.entity.CompanyEmailAdress;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyEmailServiceRepository extends BaseRepository<CompanyEmailAdress> {
    @Query("select CompanyEmailAdress from CompanyEmailAdress where company.id = ?1")
    Optional<CompanyEmailAdress> findByCompanyId(String id);



}
