package com.portifolyo.ticketservice.exception;

public class TicketNotSellException extends RuntimeException {

    public TicketNotSellException() {
        super("Tickets cannot be sold for this event");
    }
}
