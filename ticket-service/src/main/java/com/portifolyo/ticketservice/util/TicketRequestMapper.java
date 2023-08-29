package com.portifolyo.ticketservice.util;

import com.portifolyo.ticketservice.entity.Ticket;
import org.portifolyo.requests.eventservice.TicketRequest;

public class TicketRequestMapper {
    private TicketRequestMapper(){}
    public static Ticket toEntity(TicketRequest ticketRequest){
        return new Ticket(ticketRequest.name(), ticketRequest.surname(),  ticketRequest.phoneNumber(),ticketRequest.email(), ticketRequest.tcNo());
    }
    public static TicketRequest toDto(Ticket c) {
        return new TicketRequest(c.getName(),c.getSurname(),c.getPhoneNumber(),c.getEmail(),c.getTcNo(),c.getEventId(),null);
    }

}
