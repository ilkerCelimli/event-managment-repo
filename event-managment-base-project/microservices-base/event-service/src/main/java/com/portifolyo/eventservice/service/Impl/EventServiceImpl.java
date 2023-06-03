package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.EventAndOrganizatorManyToManyRepository;
import com.portifolyo.eventservice.repository.EventRepository;
import com.portifolyo.eventservice.repository.ImageAndLinksRepository;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.repository.projections.EventInfo;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import com.portifolyo.eventservice.service.*;
import com.portifolyo.eventservice.util.mapper.EventInfomapper;
import jakarta.transaction.Transactional;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl extends BaseServiceImpl<Event> implements EventService {
    private final EventRepository eventRepository;
    private final EventAreaService eventAreaService;
    private final OrganizatorService organizatorService;
    private final EventAndOrganizatorManyToManyRepository eventAndOrganizatorManyToManyRepository;
    private final EventDescriptionService eventDescriptionService;
    private final EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService;
    private final ImageAndLinksRepository imageAndLinksRepository;



    public EventServiceImpl(EventRepository eventRepository, EventAreaService eventAreaService, OrganizatorService organizatorService, EventAndOrganizatorManyToManyRepository eventAndOrganizatorManyToManyRepository, EventDescriptionService eventDescriptionService, EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService, ImageAndLinksRepository imageAndLinksRepository) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.eventAreaService = eventAreaService;
        this.organizatorService = organizatorService;
        this.eventAndOrganizatorManyToManyRepository = eventAndOrganizatorManyToManyRepository;
        this.eventDescriptionService = eventDescriptionService;
        this.eventAndOrganizatorManyToManyService = eventAndOrganizatorManyToManyService;
        this.imageAndLinksRepository = imageAndLinksRepository;
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

        try {
           UpdateHelper<EventSaveRequest,Event> updateHelper = new UpdateHelper<>();
            Event e =  updateHelper.updateHelper(event,findById(eventId));
            if(event.description() != null) {
                e.getEventDescription().setDescrtiption(event.description().description());
            }
            update(e);
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex){
            System.out.println(ex);
        }
    /*   if(event.isTicket() != e.getIsTicket()) e.setIsTicket(event.isTicket());
       if(event.isPeopleIsRegistered() != e.getIsPeopleRegistered()) e.setIsPeopleRegistered(event.isPeopleIsRegistered());
       if(event.eventName() != null) e.setName(event.eventName());
       if(event.eventDate() != null &&!event.eventDate().before(Date.from(Instant.now())) && event.eventDate().getTime() != Instant.now().getEpochSecond()){
           e.setEventDate(event.eventDate());
       }
       if(event.eventType() != null &&!event.eventType().equals(e.getEventType())) e.setEventType(event.eventType());
       if(event.maxPeople() != null) e.setMaxPeople(event.maxPeople());
       if(event.comingPeople() != null) e.setComingPeople(event.comingPeople());
       if(event.description() != null) {
           if(event.description().description() != null) e.getEventDescription().setDescrtiption(event.description().description());
       }*/
    }

    @Override
    @Transactional
    public void eventInActiveHandle(String eventId) {
        eventRepository.updateIsDeletedById(true,eventId);
    }

    @Override
    public List<EventInfo> findEventsByOrganizatorMail(String email,Integer page,Integer size) {
        List<EventInfo> list = new ArrayList<>();
        List<EventAndOrganizatorManyToMany> m =
                this.eventAndOrganizatorManyToManyRepository.findByOrganizator_Email(email,PageRequest.of(page,size));
        m.forEach(i -> {
            EventInfo eventInfo = EventInfomapper.toEntity(i.getEvent());
            EventAreaInfo info = this.eventAreaService.findEventArea(eventInfo.getId());
            eventInfo.setEventAreaInfo(info);
            List<OrganizatorInfo> organizatorInfos = new ArrayList<>();
            organizatorInfos.add(this.organizatorService.findOrganizatorByEmail(email));
            eventInfo.setOrganizatorInfos(organizatorInfos);
            eventInfo.getEventDescription().setImageAndLinksList(this.imageAndLinksRepository.findByEventDescription_Id(eventInfo.getEventDescription().getId()));
            list.add(eventInfo);
        });


        return list;
    }

    @Override
    public void addOrganizatorByEvent(String eventId, OrganizatorRequest organizatorRequest) {
        Optional<Event> event = this.eventRepository.findById(eventId);
        event.orElseThrow(() -> new NotFoundException(eventId));
        this.eventAndOrganizatorManyToManyService.saveOrganizator(List.of(organizatorRequest),event.get());
    }

    @Override
    public Event findById(String id) {
        return super.findById(id);
    }

    @Override
    public void addimages(String eventid, List<ImageAndLinks> imageAndLinks) {
        this.eventDescriptionService.addImages(findById(eventid),imageAndLinks);
    }


}
