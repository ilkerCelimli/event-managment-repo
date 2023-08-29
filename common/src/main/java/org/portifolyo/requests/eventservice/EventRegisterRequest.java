package org.portifolyo.requests.eventservice;

public record EventRegisterRequest(
        String eventId,
        String userEmail,
        boolean isTicket,
        TicketRequest ticketRequest

) {
}
