package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.EventDescriptionRepository;
import com.portifolyo.eventservice.repository.EventRepository;
import com.portifolyo.eventservice.repository.ImageAndLinksRepository;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.repository.projections.EventDto;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.EventAreaService;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.util.mapper.EventDtoMapper;
import com.portifolyo.eventservice.util.mapper.EventSaveRequestMapper;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class EventServiceImpl extends BaseServiceImpl<Event> implements EventService {

    private final EventRepository eventRepository;
    private final EventAreaService eventAreaService;
    private final EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService;
    private final ImageAndLinksRepository imageAndLinksRepository;
    private final EventDescriptionRepository eventDescriptionRepository;

    public EventServiceImpl(EventRepository eventRepository, EventAreaService eventAreaService, EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService, ImageAndLinksRepository imageAndLinksRepository, EventDescriptionRepository eventDescriptionRepository) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.eventAreaService = eventAreaService;
        this.eventAndOrganizatorManyToManyService = eventAndOrganizatorManyToManyService;
        this.imageAndLinksRepository = imageAndLinksRepository;
        this.eventDescriptionRepository = eventDescriptionRepository;
    }

    @Override
    @Transactional
    public void saveEventRequestHandle(EventSaveRequest request) {
        Event event = EventSaveRequestMapper.toEntity(request);
        EventDescription desc = this.eventDescriptionRepository.save(event.getEventDescription());
        event.setEventDescription(desc);

        if(request.description().imageAndLinksReqeusts() != null){
            request.description().imageAndLinksReqeusts().forEach(i -> {
                ImageAndLinks f = new ImageAndLinks();
                f.setEventDescription(desc);
                f.setDescriptionTypes(i.descriptionTypes());
                f.setItem(i.item());
                this.imageAndLinksRepository.save(f);
            });
        }
        Event saved = this.save(event);
        this.eventAndOrganizatorManyToManyService.saveOrganizator(request.organizatorLists(),saved);
        this.eventAreaService.handleEventAreaRequest(request.eventAreaRequest(),saved);
    }

    @Override
    public void updateEventRequestHandle(EventSaveRequest event, String eventId){
        Event e = this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(
                String.format("%s event id not found",eventId)
        ));
        UpdateHelper<EventSaveRequest,Event> updateHelper = new UpdateHelper<>();
           Event updated = updateHelper.updateHelper(event,e);
           if(updated == null) throw new GenericException("Update problems",500);
           save(updated);
    }

    @Override
    public void eventInActiveHandle(String eventId) {
        this.eventRepository.updateIsDeletedById(true,eventId);
    }
    @Override
    public Event findById(String id) {
        return this.eventRepository.findById(id).orElseThrow(()
                -> new NotFoundException(String.format("%s event id not found",id)));
    }

    @Override
    public void addimages(String eventid, List<ImageAndLinks> imageAndLinks) {
        EventDescription desc = this.eventRepository.findById(eventid).orElseThrow(() -> new NotFoundException(String.format("%s not found",eventid))).getEventDescription();
        imageAndLinks.forEach(i -> i.setEventDescription(desc));
        this.imageAndLinksRepository.saveAll(imageAndLinks);
    }

    @Override
    public List<EventDto> findEvents(TableRequest request) {
        List<EventDto> result =  new ArrayList<>();
        List<Event> events =  this.eventRepository.findAll(PageRequest.of(request.getPage(), request.getSize())).toList();
        events.forEach(i -> {
            EventAreaInfo eventAreaInfo = this.eventAreaService.findEventArea(i.getId());
            EventDto dtos = EventDtoMapper.toDto(i,eventAreaInfo,this.eventAndOrganizatorManyToManyService.findOrganizatorsByEventId(i.getId()));
            result.add(dtos);
        });


        return result;
    }
}
