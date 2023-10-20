package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.EventAndIncomingPeopleManyToMany;
import com.portifolyo.eventservice.repository.model.EventRegistered;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EventRegisteredMapper {

    public static EventRegistered toDto(String eventId, List<EventAndIncomingPeopleManyToMany> event) {
        return new EventRegistered(eventId,event.stream().map(i -> i.getIncomingPeople().getUserEmail()).toList());
    }

}
