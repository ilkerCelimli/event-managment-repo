package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.repository.projections.EventInfo;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;

import java.util.List;

public interface EventService {

    void saveEventRequestHandle(EventSaveRequest event);
    void updateEventRequestHandle(EventSaveRequest event,String eventId);

    void eventInActiveHandle(String eventId);

    List<EventInfo> findEventsByOrganizatorMail(String email,Integer page,Integer size);

    void addOrganizatorByEvent(String eventId, OrganizatorRequest organizatorRequest);
    Event findById(String id);

    void addimages(String eventid, List<ImageAndLinks> imageAndLinks);

}
