package com.portifolyo.eventservice.repository.projections;

import com.portifolyo.eventservice.entity.Ticket;
import org.portifolyo.requests.eventservice.enums.EventType;

import java.util.Date;

/**
 * Projection for {@link Ticket}
 */
public interface TicketInfo {
    String getId();

    Date getCreatedDate();

    Boolean isIsDeleted();

    String getName();

    String getSurname();

    String getPhoneNumber();

    String getEmail();

    String getTcNo();

    EventInfo1 getEvent();

    /**
     * Projection for {@link com.portifolyo.eventservice.entity.Event}
     */
    interface EventInfo1 {
        String getId();

        String getEventName();

        Date getEventDate();

        Boolean isIsTicket();

        EventType getEventType();

        int getMaxPeople();
    }
}