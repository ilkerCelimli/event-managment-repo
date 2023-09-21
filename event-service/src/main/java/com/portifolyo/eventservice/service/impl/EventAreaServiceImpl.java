package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventArea;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.EventAreaRepository;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.service.EventAreaService;
import com.portifolyo.eventservice.util.mapper.EventAreaMapper;
import org.portifolyo.requests.eventservice.EventAreaRequest;
import org.springframework.stereotype.Service;

@Service
public class EventAreaServiceImpl extends BaseServiceImpl<EventArea> implements EventAreaService {

    private final EventAreaRepository eventAreaRepository;

    public EventAreaServiceImpl(EventAreaRepository eventAreaRepository){
        super(eventAreaRepository);
        this.eventAreaRepository = eventAreaRepository;
    }

    @Override
    public void handleEventAreaRequest(EventAreaRequest areaRequest, Event e) {
        EventArea eventArea = EventAreaMapper.toEntity(areaRequest);
        eventArea.setEvent(e);
        save(eventArea);
    }

    @Override
    public EventAreaInfo findEventArea(String eventId) {
        return this.eventAreaRepository.findByEvent_Id(eventId).orElseThrow(() ->
                new NotFoundException(String.format("Not found event area %s id",eventId)));
    }
}
