package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.Organizator;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface OrganizatorRepository extends BaseRepository<Organizator>{
    @Transactional
    @Modifying
    @Query("update Organizator o set o.deleted = ?1 where o.id = ?2")
    void updateIsDeletedById(Boolean isDeleted, String id);
    Optional<Organizator> findOrganizatorByEmailEquals(String email);
}
