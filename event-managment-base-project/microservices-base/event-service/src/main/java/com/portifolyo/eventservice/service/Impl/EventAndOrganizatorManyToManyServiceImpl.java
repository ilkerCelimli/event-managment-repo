package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import com.portifolyo.eventservice.repository.EventAndOrganizatorManyToManyRepository;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.apache.commons.lang.SerializationUtils;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventAndOrganizatorManyToManyServiceImpl extends BaseServiceImpl<EventAndOrganizatorManyToMany> implements EventAndOrganizatorManyToManyService {

    private final EventAndOrganizatorManyToManyRepository eventAndOrganizatorManyToManyRepository;
    private final OrganizatorService organizatorService;
    private final RabbitTemplate rabbitTemplate;

    public EventAndOrganizatorManyToManyServiceImpl(EventAndOrganizatorManyToManyRepository eventAndOrganizatorManyToManyRepository, OrganizatorService organizatorService, RabbitTemplate rabbitTemplate) {
        super(eventAndOrganizatorManyToManyRepository);
        this.eventAndOrganizatorManyToManyRepository = eventAndOrganizatorManyToManyRepository;
        this.organizatorService = organizatorService;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Override
    public void saveOrganizator(List<OrganizatorRequest> o, Event e) {

        o.forEach(i -> {
            EventAndOrganizatorManyToMany f =
                    this.eventAndOrganizatorManyToManyRepository.
                            save(new EventAndOrganizatorManyToMany(e,organizatorService.handleOrganizator(i)));
            rabbitTemplate.convertAndSend("user-exchange","user-routing", SerializationUtils.serialize(i));
        });
    }
}
