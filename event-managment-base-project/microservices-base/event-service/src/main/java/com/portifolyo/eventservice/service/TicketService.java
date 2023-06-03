package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.repository.projections.TicketInfo;
import org.portifolyo.requests.eventservice.TicketRequest;

import java.util.List;

public interface TicketService {

    void handleTicketRequest(TicketRequest ticketRequest);
    void deleteTicket(String id);
    TicketRequest updateTicket(TicketRequest ticketRequest);

    List<TicketInfo> findTickets(Integer page, Integer size, String eventId);


}
