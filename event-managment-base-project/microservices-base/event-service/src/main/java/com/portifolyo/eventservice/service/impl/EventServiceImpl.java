package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.EventRepository;
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
import org.springframework.beans.MethodInvocationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
@Service
public class EventServiceImpl extends BaseServiceImpl<Event> implements EventService {

    private final EventRepository eventRepository;
    private final EventAreaService eventAreaService;
    private final EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService;

    public EventServiceImpl(EventRepository eventRepository, EventAreaService eventAreaService, EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.eventAreaService = eventAreaService;
        this.eventAndOrganizatorManyToManyService = eventAndOrganizatorManyToManyService;
    }

    @Override
    @Transactional
    public void saveEventRequestHandle(EventSaveRequest request) {
        Event event = EventSaveRequestMapper.toEntity(request);
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
       try {
           Event updated = updateHelper.updateHelper(event,e);
           if(e != null) {
               this.save(updated);
               return;
           }
           throw new GenericException("Update problems",500);
       }
       catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException exception){
           throw new GenericException("Update problems",500);
       }


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
        //TODO create Images Description Service
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
