package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;
import com.portifolyo.eventservice.repository.model.EventRegistered;


public interface EventAndInComingPeopleManyToManyService extends BaseService<EventAndIncomingPeopleManyToMany> {

    long findHowManyPeopleComeEvent(String eventId);

    void registerEvent(Event eventId, String userEmail);

    EventRegistered findEventRegistered(String eventId);
}
