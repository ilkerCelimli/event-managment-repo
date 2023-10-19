package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.repository.projections.EventDto;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;

import java.util.List;

public class EventDtoMapper {

    private EventDtoMapper(){}

    public static EventDto toDto(Event e, EventAreaInfo area, List<OrganizatorInfo> organizatorInfoList){
        return EventDto.builder()
                .id(e.getId())
                .createdDate(e.getCreatedDate())
                //.eventAreaInfo(area)
                .eventDescription(EventDescriptionMapper.toDto(e.getEventDescription()))
                .isDeleted(e.getDeleted())
                .isPeopleRegistered(e.isPeopleRegistered())
                .eventName(e.getEventName())
                .eventType(e.getEventType())
                .eventDate(e.getEventDate())
                .maxPeople(e.getMaxPeople())
                .isTicket(e.isTicket())
                .organizatorInfos(organizatorInfoList).build();
    }

}
