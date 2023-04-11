package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.repository.BaseRepository;
import com.portifolyo.eventservice.repository.EventRepository;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.springframework.stereotype.Service;

@Service
public class EventServiceImpl extends BaseServiceImpl<Event> implements com.portifolyo.eventservice.service.EventService {
    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        super(eventRepository);
        this.eventRepository = eventRepository;
    }

    @Override
    public void saveEventRequestHandle(EventSaveRequest event) {
        
    }
}
