package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.ComingPeople;
import org.portifolyo.requests.eventservice.TicketRequest;

public class TicketRequestMapper {
    private TicketRequestMapper(){}
    public static ComingPeople toEntity(TicketRequest ticketRequest){
        return new ComingPeople(ticketRequest.name(), ticketRequest.surname(),  ticketRequest.phoneNumber(),ticketRequest.email(), ticketRequest.tcNo());
    }
    public static TicketRequest toDto(ComingPeople c) {
        return new TicketRequest(c.getName(),c.getSurname(),c.getPhoneNumber(),c.getEmail(),c.getTcNo(),null);
    }

}
