package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.repository.projections.EventInfo;
import org.portifolyo.requests.eventservice.EventSaveRequest;

import java.util.List;

public interface EventService {

    void saveEventRequestHandle(EventSaveRequest event);
    void updateEventRequestHandle(EventSaveRequest event,String eventId);

    void eventInActiveHandle(String eventId);

    List<EventInfo> findEventsByOrganizatorMail(String email);
}
