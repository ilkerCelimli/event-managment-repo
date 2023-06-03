package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.ComingPeople;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComingPeopleRepository extends BaseRepository<ComingPeople> {
    @Transactional
    @Modifying
    @Query("update ComingPeople c set c.isDeleted = ?1 where c.id = ?2")
    int updateIsDeletedById(Boolean isDeleted, String id);

    List<TicketInfo> findByEvent_IdOrderByCreatedDateDesc(String id, Pageable pageable);


}
