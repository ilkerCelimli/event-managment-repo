package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventArea;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.EventAreaRepository;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAreaService;
import com.portifolyo.eventservice.util.mapper.EventAreaMapper;
import org.portifolyo.requests.eventservice.EventAreaRequest;
import org.springframework.stereotype.Service;

@Service
public class EventAreaServiceImpl extends BaseServiceImpl<EventArea> implements EventAreaService {
    private final EventAreaRepository eventAreaRepository;

    public EventAreaServiceImpl(EventAreaRepository repository) {
        super(repository);
        this.eventAreaRepository = repository;
    }

    @Override
    public void handleEventAreaRequest(EventAreaRequest areaRequest, Event event) {
        EventArea e = EventAreaMapper.toEntity(areaRequest);
        e.setEvent(event);
        save(e);
    }

    @Override
    public EventAreaInfo findEventArea(String eventId) {
        return this.eventAreaRepository.findByEvent_Id(eventId).orElseThrow(() -> new NotFoundException(eventId));
    }
}
