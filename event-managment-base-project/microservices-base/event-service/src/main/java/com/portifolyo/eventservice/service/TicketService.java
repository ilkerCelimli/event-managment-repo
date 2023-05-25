package com.portifolyo.eventservice.service;

import org.portifolyo.requests.eventservice.TicketRequest;

public interface TicketService {

    void handleTicketRequest(TicketRequest ticketRequest);
    void deleteTicket(String id);
    TicketRequest updateTicket(TicketRequest ticketRequest);


}
