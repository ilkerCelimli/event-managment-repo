package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;
import com.portifolyo.eventservice.repository.EventAndIncomingPeopleManyToManyRepository;
import com.portifolyo.eventservice.service.EventAndInComingPeopleManyToManyService;
import org.springframework.stereotype.Service;

@Service
public class EventAndInComingPeopleManyToManyServiceImpl extends BaseServiceImpl<EventAndIncomingPeopleManyToMany> implements EventAndInComingPeopleManyToManyService {

    private final EventAndIncomingPeopleManyToManyRepository repository;

    public EventAndInComingPeopleManyToManyServiceImpl(EventAndIncomingPeopleManyToManyRepository repository) {
        super(repository);
        this.repository = repository;
    }


    @Override
    public long findHowManyPeopleComeEvent(String eventId) {
        return this.repository.ComingPeopleCountByEventId(eventId);
    }
}
