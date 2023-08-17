package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Ticket;
import org.portifolyo.requests.eventservice.TicketRequest;

public class TicketRequestMapper {
    private TicketRequestMapper(){}
    public static Ticket toEntity(TicketRequest ticketRequest){
        return new Ticket(ticketRequest.name(), ticketRequest.surname(),  ticketRequest.phoneNumber(),ticketRequest.email(), ticketRequest.tcNo());
    }
    public static TicketRequest toDto(Ticket c) {
        return new TicketRequest(c.getName(),c.getSurname(),c.getPhoneNumber(),c.getEmail(),c.getTcNo(),null);
    }

}
