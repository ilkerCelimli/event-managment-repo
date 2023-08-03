package com.portifolyo.eventservice.exceptions;

public class TicketNotSellException extends RuntimeException {

    public TicketNotSellException() {
        super("Tickets cannot be sold for this event");
    }
}
