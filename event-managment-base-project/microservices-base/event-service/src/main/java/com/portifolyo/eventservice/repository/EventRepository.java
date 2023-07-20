package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.Event;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EventRepository extends BaseRepository<Event> {
    @Transactional
    @Modifying
    @Query("update Event e set e.deleted = ?1 where e.id = ?2")
    int updateIsDeletedById(Boolean isDeleted, String id);



}
