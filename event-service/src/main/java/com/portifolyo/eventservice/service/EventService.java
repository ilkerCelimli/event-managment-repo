package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.repository.model.EventDto;
import com.portifolyo.eventservice.repository.model.EventRegistered;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.EventRegisterRequest;
import org.portifolyo.requests.eventservice.EventSaveRequest;

import java.util.List;

public interface EventService extends BaseService<Event> {

    Event saveEventRequestHandle(EventSaveRequest event,String eventOwner);
    Event updateEventRequestHandle(EventSaveRequest event,String eventId);
    void eventInActiveHandle(String eventId);
    void addimages(String eventid, List<ImageAndLinks> imageAndLinks);
    List<EventDto> findEvents(TableRequest request);
    EventDto findEventById(String id);
    void eventRegister(String eventId, EventRegisterRequest eventRegisterRequest);
    EventRegistered findEventRegisteredPeople(String eventId);

}
