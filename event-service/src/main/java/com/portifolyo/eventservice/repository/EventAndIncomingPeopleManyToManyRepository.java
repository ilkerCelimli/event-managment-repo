package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventAndIncomingPeopleManyToManyRepository extends BaseRepository<EventAndIncomingPeopleManyToMany>{
    @Query("select count(e) from EventAndIncomingPeopleManyToMany e where e.eventId.id = ?1")
    long ComingPeopleCountByEventId(String id);

    List<EventAndIncomingPeopleManyToMany> findByEventId_Id(String id);






}
