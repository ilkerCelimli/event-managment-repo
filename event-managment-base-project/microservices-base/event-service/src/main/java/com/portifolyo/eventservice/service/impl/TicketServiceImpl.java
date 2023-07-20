package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Ticket;
import com.portifolyo.eventservice.repository.TicketRepository;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.TicketService;
import org.apache.commons.lang3.NotImplementedException;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketServiceImpl extends BaseServiceImpl<Ticket> implements TicketService {

   private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        super(ticketRepository);
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void handleTicketRequest(TicketRequest ticketRequest) {
        throw new NotImplementedException();
    }

    @Override
    public void deleteTicket(String id) {
        throw new NotImplementedException();

    }

    @Override
    public TicketRequest updateTicket(TicketRequest ticketRequest) {
        throw new NotImplementedException();

    }

    @Override
    public List<TicketInfo> findTickets(Integer page, Integer size, String eventId) {
        throw new NotImplementedException();

    }
}
