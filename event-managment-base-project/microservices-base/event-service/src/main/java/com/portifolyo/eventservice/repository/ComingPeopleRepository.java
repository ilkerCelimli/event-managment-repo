package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.ComingPeople;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface ComingPeopleRepository extends BaseRepository<ComingPeople> {
    @Transactional
    @Modifying
    @Query("update ComingPeople c set c.isDeleted = ?1 where c.id = ?2")
    int updateIsDeletedById(Boolean isDeleted, String id);
}
