package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.EventArea;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;

import java.util.List;
import java.util.Optional;

public interface EventAreaRepository extends BaseRepository<EventArea> {
    Optional<EventAreaInfo> findByEvent_Id(String id);
}
