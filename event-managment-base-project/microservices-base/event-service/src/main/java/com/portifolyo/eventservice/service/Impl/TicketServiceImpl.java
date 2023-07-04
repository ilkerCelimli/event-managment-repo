package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.ComingPeople;
import com.portifolyo.eventservice.repository.ComingPeopleRepository;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.service.TicketService;
import com.portifolyo.eventservice.util.mapper.TicketRequestMapper;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Service
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

/*        if(ticketRequest.email() != null) c.setEmail(ticketRequest.email());
        if(ticketRequest.name() != null) c.setName(ticketRequest.name());
        if(ticketRequest.surname() != null) c.setSurname(ticketRequest.surname());
        if(ticketRequest.tcNo() != null) c.setTcNo(ticketRequest.tcNo());
        if(ticketRequest.phoneNumber() != null) c.setPhoneNumber(ticketRequest.phoneNumber());*/

        UpdateHelper<TicketRequest, ComingPeople> updateHelper = new UpdateHelper<>();
        try {
            ComingPeople updated = updateHelper.updateHelper(ticketRequest,c);
            c = update(updated);
            return TicketRequestMapper.toDto(c);
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            System.out.println(ex);
        }
        return null;



    }

    @Override
    public List<TicketInfo> findTickets(Integer page, Integer size, String eventId) {
        return this.comingPeopleRepository.findByEvent_IdOrderByCreatedDateDesc(eventId, PageRequest.of(page,size));
    }
}
