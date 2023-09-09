package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.feign.TicketServiceFeignClient;
import com.portifolyo.eventservice.feign.UserServiceFeignClient;
import com.portifolyo.eventservice.repository.EventDescriptionRepository;
import com.portifolyo.eventservice.repository.EventRepository;
import com.portifolyo.eventservice.repository.ImageAndLinksRepository;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.repository.projections.EventDto;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.EventAndInComingPeopleManyToManyService;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.EventAreaService;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.util.mapper.EventDtoMapper;
import com.portifolyo.eventservice.util.mapper.EventSaveRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.EventRegisterRequest;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.response.UserInfo;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class EventServiceImpl extends BaseServiceImpl<Event> implements EventService {

    private final EventRepository eventRepository;
    private final EventAreaService eventAreaService;
    private final EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService;
    private final ImageAndLinksRepository imageAndLinksRepository;
    private final EventDescriptionRepository eventDescriptionRepository;
    private final UserServiceFeignClient userServiceFeignClient;

    private final TicketServiceFeignClient ticketServiceFeignClient;
    private final RabbitTemplate rabbitTemplate;
    private final EventAndInComingPeopleManyToManyService eventAndInComingPeopleManyToManyService;

    public EventServiceImpl(EventRepository eventRepository, EventAreaService eventAreaService, EventAndOrganizatorManyToManyService eventAndOrganizatorManyToManyService, ImageAndLinksRepository imageAndLinksRepository, EventDescriptionRepository eventDescriptionRepository, UserServiceFeignClient userServiceFeignClient, TicketServiceFeignClient ticketServiceFeignClient, RabbitTemplate rabbitTemplate, EventAndInComingPeopleManyToManyService eventAndInComingPeopleManyToManyService) {
        super(eventRepository);
        this.eventRepository = eventRepository;
        this.eventAreaService = eventAreaService;
        this.eventAndOrganizatorManyToManyService = eventAndOrganizatorManyToManyService;
        this.imageAndLinksRepository = imageAndLinksRepository;
        this.eventDescriptionRepository = eventDescriptionRepository;
        this.userServiceFeignClient = userServiceFeignClient;
        this.ticketServiceFeignClient = ticketServiceFeignClient;
        this.rabbitTemplate = rabbitTemplate;
        this.eventAndInComingPeopleManyToManyService = eventAndInComingPeopleManyToManyService;
    }

    @Override
    @Transactional
    public Event saveEventRequestHandle(EventSaveRequest request, String eventOwner, String token) {
        Event event = EventSaveRequestMapper.toEntity(request);
        EventDescription desc = this.eventDescriptionRepository.save(event.getEventDescription());
        event.setEventDescription(desc);

        if (request.description().imageAndLinksReqeusts() != null) {
            request.description().imageAndLinksReqeusts().forEach(i -> {
                ImageAndLinks f = new ImageAndLinks();
                f.setEventDescription(desc);
                f.setDescriptionTypes(i.descriptionTypes());
                f.setItem(i.item());
                this.imageAndLinksRepository.save(f);
            });
        }

        ResponseEntity<GenericResponse<UserInfo>> response = this.userServiceFeignClient.findById(eventOwner, token);
        if (response.getBody() == null && response.getBody().getData() == null) {
            throw new GenericException("User not found", 404);
        }

        UserInfo userInfo = response.getBody().getData();
            event.setEventOwner(userInfo.id());

        List<OrganizatorRequest> organizators = handleOrganizatorRequests(request.organizatorLists(), userInfo);
        Event saved = this.save(event);
        this.eventAndOrganizatorManyToManyService.saveOrganizator(organizators, saved);
        this.eventAreaService.handleEventAreaRequest(request.eventAreaRequest(), saved);
        return saved;
    }

    @Override
    public Event updateEventRequestHandle(EventSaveRequest event, String eventId) {
        Event e = this.eventRepository.findById(eventId).orElseThrow(() -> new NotFoundException(
                String.format("%s event id not found", eventId)
        ));
        UpdateHelper<EventSaveRequest, Event> updateHelper = new UpdateHelper<>();
        Event updated = updateHelper.updateHelper(event, e);
        if (updated == null) throw new GenericException("Update problems", 500);
        return save(updated);
    }

    @Override
    public void eventInActiveHandle(String eventId) {
        this.eventRepository.updateIsDeletedById(true, eventId);
    }


    @Override
    public void addimages(String eventid, List<ImageAndLinks> imageAndLinks) {
        EventDescription desc = this.eventRepository.findById(eventid).orElseThrow(() -> new NotFoundException(String.format("%s not found", eventid))).getEventDescription();
        imageAndLinks.forEach(i -> i.setEventDescription(desc));
        this.imageAndLinksRepository.saveAll(imageAndLinks);
    }

    @Override
    public List<EventDto> findEvents(TableRequest request) {
        List<EventDto> result = new ArrayList<>();
        List<Event> events = this.eventRepository.findAll(PageRequest.of(request.getPage(), request.getSize())).toList();
        events.forEach(i -> {
            EventAreaInfo eventAreaInfo = this.eventAreaService.findEventArea(i.getId());
            EventDto dtos = EventDtoMapper.toDto(i, eventAreaInfo, this.eventAndOrganizatorManyToManyService.findOrganizatorsByEventId(i.getId()));
            result.add(dtos);
        });


        return result;
    }

    @Override
    public EventDto findEventById(String id) {
        Event e = this.eventRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format("%s bulunamadı", id)));
        List<OrganizatorInfo> list = this.eventAndOrganizatorManyToManyService.findOrganizatorsByEventId(id);
        return EventDtoMapper.toDto(e, this.eventAreaService.findEventArea(id), list);
    }

    @Override
    @Transactional
    public void eventRegister(String eventId, EventRegisterRequest request) {
        Event ref = findById(eventId);
        if(!ref.isTicket()) {
            this.eventAndInComingPeopleManyToManyService.registerEvent(ref, request.userEmail());
            return;
        }
        ResponseEntity<GenericResponse<Void>> response = this.ticketServiceFeignClient.saveTicket(null,request.ticketRequest());
        if(response.getStatusCode().is2xxSuccessful()) {
            this.eventAndInComingPeopleManyToManyService.registerEvent(ref,request.userEmail());
        }
    }

    private List<OrganizatorRequest> handleOrganizatorRequests(List<OrganizatorRequest> request, UserInfo userInfo) {
        if (request == null) {
            List<OrganizatorRequest> list = new ArrayList<>();
            list.add(new OrganizatorRequest(userInfo.name(), userInfo.surname(), "", userInfo.email(), ""));
            return list;
        }
        List<OrganizatorRequest> filter = request.stream().filter(i -> i.email().equals(userInfo.email())).toList();
        if (filter.isEmpty()) {
            request.add(new OrganizatorRequest(userInfo.name(), userInfo.surname(), "", userInfo.email(), ""));
            return request;
        }
        return request;
    }

}
