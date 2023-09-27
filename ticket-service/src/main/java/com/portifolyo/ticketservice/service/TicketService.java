package com.portifolyo.ticketservice.service;


import com.portifolyo.ticketservice.repository.TicketInfo;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.TicketRequest;

import java.util.List;

public interface TicketService {

    void handleTicketRequest(TicketRequest ticketRequest);
    void deleteTicket(String id);
    TicketRequest updateTicket(String id ,TicketRequest ticketRequest);

    List<TicketInfo> findTickets(TableRequest tableRequest, String eventId);

    void handleTicketRequestQueue(byte[] bytes);


}
