package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;
import com.portifolyo.eventservice.entity.IncomingPeople;
import com.portifolyo.eventservice.repository.EventAndIncomingPeopleManyToManyRepository;
import com.portifolyo.eventservice.repository.IncomingPeopleRepository;
import com.portifolyo.eventservice.repository.model.EventRegistered;
import com.portifolyo.eventservice.service.EventAndInComingPeopleManyToManyService;
import com.portifolyo.eventservice.util.mapper.EventRegisteredMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventAndInComingPeopleManyToManyServiceImpl extends BaseServiceImpl<EventAndIncomingPeopleManyToMany> implements EventAndInComingPeopleManyToManyService {

    private final EventAndIncomingPeopleManyToManyRepository repository;
    private final IncomingPeopleRepository incomingPeopleRepository;

    public EventAndInComingPeopleManyToManyServiceImpl(EventAndIncomingPeopleManyToManyRepository repository, IncomingPeopleRepository incomingPeopleRepository) {
        super(repository);
        this.repository = repository;
        this.incomingPeopleRepository = incomingPeopleRepository;
    }


    @Override
    public long findHowManyPeopleComeEvent(String eventId) {
        return this.repository.ComingPeopleCountByEventId(eventId);
    }

    @Override
    public void registerEvent(Event eventId, String userEmail) {
        if (eventId.getMaxPeople() > this.repository.ComingPeopleCountByEventId(eventId.getId())) {
            IncomingPeople incomingPeople = new IncomingPeople();
            incomingPeople.setUserEmail(userEmail);
            incomingPeople = this.incomingPeopleRepository.save(incomingPeople);
            EventAndIncomingPeopleManyToMany ref = new EventAndIncomingPeopleManyToMany(incomingPeople, eventId);
            save(ref);
        }


    }

    @Override
    public EventRegistered findEventRegistered(String eventId) {

        return EventRegisteredMapper.toDto(eventId,this.repository.findByEventId_Id(eventId));
    }
}
