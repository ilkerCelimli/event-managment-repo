package org.portifolyo.requests.eventservice;

public record TicketRequest (
        String name,
        String surname,
        String phoneNumber,
        String email,
        String tcNo,
        String eventId
) {


}
