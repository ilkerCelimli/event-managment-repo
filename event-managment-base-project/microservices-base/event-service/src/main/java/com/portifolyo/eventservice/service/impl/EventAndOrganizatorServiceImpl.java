package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.EventAndOrganizatorManyToManyRepository;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.repository.projections.EventDto;
import com.portifolyo.eventservice.repository.projections.OrganizatorEventsInfos;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.EventAreaService;
import com.portifolyo.eventservice.util.mapper.EventDtoMapper;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EventAndOrganizatorServiceImpl extends BaseServiceImpl<EventAndOrganizatorManyToMany> implements EventAndOrganizatorManyToManyService {

    final EventAndOrganizatorManyToManyRepository repository;

    final EventAreaService eventAreaService;
    final OrganizatorRepository organizatorRepository;

    public EventAndOrganizatorServiceImpl(EventAndOrganizatorManyToManyRepository repository, EventAreaService eventAreaService, OrganizatorRepository organizatorRepository) {
        super(repository);
        this.repository = repository;
        this.eventAreaService = eventAreaService;
        this.organizatorRepository = organizatorRepository;
    }

    @Override
    public void saveOrganizator(List<OrganizatorRequest> o, Event e) {
        o.forEach(i -> {
            Optional<Organizator> organizator =  organizatorRepository.findOrganizatorByEmailEquals(i.email());
           Organizator ref = organizator.orElse(OrganizatorRequestMapper.toEntity(i));
           EventAndOrganizatorManyToMany entity = new EventAndOrganizatorManyToMany();
           entity.setOrganizator(ref);
           entity.setEvent(e);
           save(entity);
        });
    }

    @Override
    public List<OrganizatorInfo> findOrganizatorsByEventId(String id) {
        List<OrganizatorInfo> result = new ArrayList<>();
        this.repository.findOrganizatorsByEventId(id).forEach(i -> {
            OrganizatorInfo organizatorInfo = OrganizatorInfo.builder()
                    .id(i.getOrganizator().getId())
                    .name(i.getOrganizator().getName())
                    .mail(i.getOrganizator().getEmail())
                    .tcNo(i.getOrganizator().getTcNo())
                    .phoneNumber(i.getOrganizator().getPhoneNumber()).build();
            result.add(organizatorInfo);
        });
        return result;
    }

    @Override
    public OrganizatorEventsInfos complitionOrganizatorEvents(String email, TableRequest request) {
        List<EventAndOrganizatorManyToMany> lists =  this.repository.findOrganizatorAndEventsByOrganizatorEmail(email, PageRequest.of(request.getPage(),request.getSize()));
        Organizator organizator = lists.get(0).getOrganizator();
        List<EventDto> eventList = new ArrayList<>();
        if(organizator == null) throw new NotFoundException(email);
        OrganizatorEventsInfos organizatorEventsInfos = OrganizatorEventsInfos.buildOrganizatorEntity(organizator);
        lists.forEach(i -> {
            EventAreaInfo eventAreaInfo =  this.eventAreaService.findEventArea(i.getEvent().getId());
            EventDto eventDto = EventDtoMapper.toDto(i.getEvent(),eventAreaInfo,null);
            eventList.add(eventDto);
        });
        organizatorEventsInfos.setEventList(eventList);
        return organizatorEventsInfos;
    }


}
