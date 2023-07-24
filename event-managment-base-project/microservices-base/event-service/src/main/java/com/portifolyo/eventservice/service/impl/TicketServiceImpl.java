package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Ticket;
import com.portifolyo.eventservice.repository.TicketRepository;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.service.TicketService;
import com.portifolyo.eventservice.util.mapper.TicketRequestMapper;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends BaseServiceImpl<Ticket> implements TicketService {

   private final TicketRepository ticketRepository;
   private final EventService eventService;

    public TicketServiceImpl(TicketRepository ticketRepository, EventService eventService) {
        super(ticketRepository);
        this.ticketRepository = ticketRepository;
        this.eventService = eventService;
    }

    @Override
    public void handleTicketRequest(TicketRequest ticketRequest) {
       Ticket ticket = TicketRequestMapper.toEntity(ticketRequest);
       ticket.setEvent(this.eventService.findById(ticketRequest.eventId()));
       super.save(ticket);
    }

    @Override
    public void deleteTicket(String id) {
        Ticket ticket = this.findById(id);
        ticket.setDeleted(true);
        update(ticket);

    }

    @Override
    public TicketRequest updateTicket(String id ,TicketRequest ticketRequest) {
        UpdateHelper<TicketRequest,Ticket> updateHelper = new UpdateHelper<>();

        Ticket updated = updateHelper.updateHelper(ticketRequest,findById(id));
        updated = update(updated);
        return TicketRequestMapper.toDto(updated);
    }

    @Override
    public List<TicketInfo> findTickets(TableRequest tableRequest, String eventId) {
        return this.ticketRepository.findTicketsInfo(eventId,PageRequest.of(tableRequest.getPage(), tableRequest.getSize()));
    }
}
