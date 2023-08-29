package com.portifolyo.ticketservice.repository;

import org.portifolyo.requests.eventservice.enums.EventType;

import java.time.LocalDateTime;

/**
 * Projection for {@link com.portifolyo.eventservice.entity.Ticket}
 */
public interface TicketInfo {
    String getId();

    LocalDateTime getCreatedDate();

    Boolean getDeleted();

    String getName();

    String getSurname();

    String getPhoneNumber();

    String getEmail();

    String getTcNo();

    TicketEventInfo getEvent();

    /**
     * Projection for {@link com.portifolyo.eventservice.entity.Event}
     */
    interface TicketEventInfo {
        String getId();

        LocalDateTime getCreatedDate();

        Boolean getDeleted();

        String getEventName();

        LocalDateTime getEventDate();

        EventType getEventType();
    }
}