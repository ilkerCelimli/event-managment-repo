package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.ComingPeople;
import com.portifolyo.eventservice.repository.ComingPeopleRepository;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.service.TicketService;
import com.portifolyo.eventservice.util.mapper.TicketRequestMapper;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
@Slf4j
public class TicketServiceImpl extends BaseServiceImpl<ComingPeople> implements TicketService {

    private final ComingPeopleRepository comingPeopleRepository;
    private final EventService eventService;

    public TicketServiceImpl(ComingPeopleRepository comingPeopleRepository, EventService eventService) {
       super(comingPeopleRepository);
        this.comingPeopleRepository = comingPeopleRepository;
        this.eventService = eventService;
    }

    @Override
    public void handleTicketRequest(TicketRequest ticketRequest) {
        ComingPeople c = TicketRequestMapper.toEntity(ticketRequest);
        c.setEvent(eventService.findById(ticketRequest.eventId()));
        this.comingPeopleRepository.save(c);
    }

    @Override
    public void deleteTicket(String id) {
        comingPeopleRepository.updateIsDeletedById(true,id);
    }

    @Override
    public TicketRequest updateTicket(TicketRequest ticketRequest) {
        ComingPeople c = findById(ticketRequest.eventId());
        UpdateHelper<TicketRequest, ComingPeople> updateHelper = new UpdateHelper<>();
        try {
            ComingPeople updated = updateHelper.updateHelper(ticketRequest,c);
            c = update(updated);
            return TicketRequestMapper.toDto(c);
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
           log.error(ex.getMessage());
        }
        return null;



    }

    @Override
    public List<TicketInfo> findTickets(Integer page, Integer size, String eventId) {
        return this.comingPeopleRepository.findByEvent_IdOrderByCreatedDateDesc(eventId, PageRequest.of(page,size));
    }
}
