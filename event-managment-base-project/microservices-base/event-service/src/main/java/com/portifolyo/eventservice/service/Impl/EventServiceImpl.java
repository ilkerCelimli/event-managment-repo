package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.repository.EventAndOrganizatorManyToManyRepository;
import com.portifolyo.eventservice.repository.EventRepository;
import com.portifolyo.eventservice.service.*;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.util.Strings;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.TemporalField;
import java.util.Date;

@Service
public class EventServiceImpl extends BaseServiceImpl<Event> implements com.portifolyo.eventservice.service.EventService {
    private final EventRepository eventRepository;
    private final EventAreaService eventAreaService;
    private final OrganizatorService organizatorService;
    private final EventAndOrganizatorManyToManyRepository eventAndOrganizatorManyToManyRepository;
    private final EventDescriptionService eventDescriptionService;
    private final EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService;



    public EventServiceImpl(EventRepository eventRepository, EventAreaService eventAreaService, OrganizatorService organizatorService, EventAndOrganizatorManyToManyRepository eventAndOrganizatorManyToManyRepository, EventDescriptionService eventDescriptionService, EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.eventAreaService = eventAreaService;
        this.organizatorService = organizatorService;
        this.eventAndOrganizatorManyToManyRepository = eventAndOrganizatorManyToManyRepository;
        this.eventDescriptionService = eventDescriptionService;
        this.eventAndOrganizatorManyToManyService = eventAndOrganizatorManyToManyService;
    }

    @Override
    @Transactional
    public void saveEventRequestHandle(EventSaveRequest event) {
        if(event.maxPeople() > event.eventAreaRequest().areaCapacity()) throw new GenericException("area capacity is not small max people",404);
        if(event.comingPeople() > event.eventAreaRequest().areaCapacity()) throw new GenericException("area capacity is not small coming people",404);
        if(event.eventDate().before(Date.from(Instant.now()))) throw new GenericException("event date is not now",404);
       Event e =  save(new Event(event.eventName(),event.eventDate(),event.comingPeople(),event.isTicket(),
                event.isPeopleIsRegistered(),event.eventType(),event.maxPeople(), eventDescriptionService.eventDescriptionHandler(event.description())));
        eventAndOrganizatorManyToManyService.saveOrganizator(event.organizatorLists(),e);
        this.eventAreaService.handleEventAreaRequest(event.eventAreaRequest(),e);
    }

    @Override
    public void updateEventRequestHandle(EventSaveRequest event,String eventId) {
       // TODO refactor this.

        Event e =  findById(eventId);

       if(event.isTicket() != e.getIsTicket()) e.setIsTicket(event.isTicket());
       if(event.isPeopleIsRegistered() != e.getIsPeopleRegistered()) e.setIsPeopleRegistered(event.isPeopleIsRegistered());
       if(event.eventName() != null) e.setName(event.eventName());
       if(!event.eventDate().before(Date.from(Instant.now())) && event.eventDate().getTime() != Instant.now().getEpochSecond()){
           e.setEventDate(event.eventDate());
       }
       if(!event.eventType().equals(e.getEventType())) e.setEventType(event.eventType());
       if(event.maxPeople() != null) e.setMaxPeople(event.maxPeople());
       if(event.comingPeople() != null) e.setComingPeople(event.comingPeople());
       if(event.description() != null) {
           if(event.description().description() != null) e.getEventDescription().setDescrtiption(event.description().description());
       }
       update(e);
    }
}
