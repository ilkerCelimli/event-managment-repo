package com.portifolyo.ticketservice.service;

import com.portifolyo.ticketservice.entity.Ticket;
import com.portifolyo.ticketservice.exception.TicketNotSellException;
import com.portifolyo.ticketservice.repository.TicketInfo;
import com.portifolyo.ticketservice.repository.TicketRepository;
import com.portifolyo.ticketservice.util.TicketRequestMapper;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;

    public TicketServiceImpl(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Override
    public void handleTicketRequest(TicketRequest ticketRequest) {
        if(!ticketRequest.eventStartDate().equals(new Date()) || !ticketRequest.eventStartDate().after(new Date()) ) {
        Ticket ticket = TicketRequestMapper.toEntity(ticketRequest);
        ticketRepository.save(ticket);
        }
        throw new TicketNotSellException();
    }

    @Override
    public void deleteTicket(String id) {
        Ticket ticket = this.ticketRepository.findById(id).orElseThrow(TicketNotSellException::new);
        ticket.setDeleted(true);
        this.ticketRepository.save(ticket);

    }

    @Override
    public TicketRequest updateTicket(String id, TicketRequest ticketRequest) {
        UpdateHelper<TicketRequest, Ticket> updateHelper = new UpdateHelper<>();
        Ticket updated = this.ticketRepository.findById(id).orElseThrow(RuntimeException::new);
        updated = updateHelper.updateHelper(ticketRequest,updated);
        updated = this.ticketRepository.save(updated);
        return TicketRequestMapper.toDto(updated);
    }

    @Override
    public List<TicketInfo> findTickets(TableRequest tableRequest, String eventId) {
        return this.ticketRepository.findByDeletedFalseAndEvent_IdOrderByCreatedDateAsc(eventId, PageRequest.of(tableRequest.getPage(), tableRequest.getSize()));
    }
}
