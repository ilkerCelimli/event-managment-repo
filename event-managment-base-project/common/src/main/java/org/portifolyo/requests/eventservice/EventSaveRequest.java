package org.portifolyo.requests.eventservice;

import org.portifolyo.requests.eventservice.enums.EventType;

import java.util.Date;
import java.util.List;

public record EventSaveRequest(
        List<String> organizatorLists,
        String eventName,
        Date eventDate,
        Integer comingPeople,
        Boolean isTicket,
        EventType eventType,
        Integer maxPeople,
        EventDescriptionRequest eventDescription
) {
}
