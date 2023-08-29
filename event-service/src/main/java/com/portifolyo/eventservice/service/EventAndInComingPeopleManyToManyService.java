package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;

public interface EventAndInComingPeopleManyToManyService extends BaseService<EventAndIncomingPeopleManyToMany> {

    long findHowManyPeopleComeEvent(String eventId);


}
