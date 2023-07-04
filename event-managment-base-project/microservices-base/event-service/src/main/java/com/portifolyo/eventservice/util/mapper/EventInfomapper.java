package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.repository.projections.EventDescriptionInfo;
import com.portifolyo.eventservice.repository.projections.EventInfo;

public class EventInfomapper {

   public static EventInfo toEntity(Event e){
        EventInfo event = new EventInfo();
        event.setId(e.getId());
        event.setName(e.getEventName());
        event.setEventDate(e.getEventDate());
        event.setEventType(e.getEventType());
        event.setMaxPeople(e.getMaxPeople());
        event.setComingPeople(e.getComingPeople());
        event.setIsPeopleRegistered(e.getIsPeopleRegistered());
        event.setIsTicket(e.getIsTicket());
        EventDescriptionInfo desc = new EventDescriptionInfo();
        desc.setDescrtiption(e.getEventDescription().getDescrtiption());
        desc.setId(e.getEventDescription().getId());
        desc.setIsDeleted(e.getEventDescription().getIsDeleted());
        event.setEventDescription(desc);
        return event;
    }
    private EventInfomapper(){}

}
