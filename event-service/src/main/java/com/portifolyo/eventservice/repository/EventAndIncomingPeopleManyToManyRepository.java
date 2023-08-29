package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;
import org.springframework.data.jpa.repository.Query;

public interface EventAndIncomingPeopleManyToManyRepository extends BaseRepository<EventAndIncomingPeopleManyToMany>{
    @Query("select count(e) from EventAndIncomingPeopleManyToMany e where e.eventId.id = ?1")
    long ComingPeopleCountByEventId(String id);





}
