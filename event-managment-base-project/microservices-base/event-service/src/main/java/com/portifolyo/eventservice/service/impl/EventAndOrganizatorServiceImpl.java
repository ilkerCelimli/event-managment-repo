package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.repository.BaseRepository;
import com.portifolyo.eventservice.repository.EventAndOrganizatorManyToManyRepository;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class EventAndOrganizatorServiceImpl extends BaseServiceImpl<EventAndOrganizatorManyToMany> implements EventAndOrganizatorManyToManyService {

    final OrganizatorService organizatorService;
    final EventAndOrganizatorManyToManyRepository repository;

    public EventAndOrganizatorServiceImpl(OrganizatorService organizatorService, EventAndOrganizatorManyToManyRepository repository) {
        super(repository);
        this.organizatorService = organizatorService;
        this.repository = repository;
    }

    @Override
    public void saveOrganizator(List<OrganizatorRequest> o, Event e) {
        o.forEach(i -> {
           Organizator ref = OrganizatorRequestMapper.toEntity(i);
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


}
