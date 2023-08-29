package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;

public interface EventAndInComingPeopleManyToManyService extends BaseService<EventAndIncomingPeopleManyToMany> {

    long findHowManyPeopleComeEvent(String eventId);

    void registerEvent(Event eventId, String userEmail);

}
