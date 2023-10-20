package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.EventArea;

import java.util.Optional;

public interface EventAreaRepository extends BaseRepository<EventArea> {

    Optional<EventArea> findByEvent_Id(String id);
}
