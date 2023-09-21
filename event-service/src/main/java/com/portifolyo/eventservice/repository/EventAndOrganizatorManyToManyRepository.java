package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EventAndOrganizatorManyToManyRepository extends BaseRepository<EventAndOrganizatorManyToMany> {
    @Query("select e from EventAndOrganizatorManyToMany e where e.event.id = ?1")
    List<EventAndOrganizatorManyToMany> findOrganizatorsByEventId(String id);

    @Query("select e from EventAndOrganizatorManyToMany e where e.organizator.email = ?1 order by e.event.createdDate")
    List<EventAndOrganizatorManyToMany> findOrganizatorAndEventsByOrganizatorEmail(String email, Pageable pageable);





}
