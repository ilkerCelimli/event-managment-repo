package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface OrganizatorRepository extends BaseRepository<Organizator>{
    @Query("select o from Organizator o where o.email = ?1")
    Optional<OrganizatorInfo> findByEmail(String email);
    Optional<Organizator> findOrganizatorByEmailEquals(String email);
    boolean existsByEmail(String email);
}
