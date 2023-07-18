package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import com.portifolyo.eventservice.repository.projections.EventDescriptionInfo;
import com.portifolyo.eventservice.repository.projections.EventDto;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;

import java.util.List;

public class EventDtoMapper {

    private EventDtoMapper(){}

    public static EventDto toDto(Event e, EventAreaInfo area, List<OrganizatorInfo> organizatorInfoList){
        return EventDto.builder()
                .id(e.getId())
                .createdDate(e.getCreatedDate())
                .eventAreaInfo(area)
                .eventDescription(EventDescriptionMapper.toDto(e.getEventDescription()))
                .isDeleted(e.getIsDeleted())
                .isPeopleRegistered(e.getIsPeopleRegistered())
                .comingPeople(e.getComingPeople())
                .eventName(e.getEventName())
                .eventType(e.getEventType())
                .eventDate(e.getEventDate())
                .maxPeople(e.getMaxPeople())
                .isTicket(e.getIsTicket())
                .organizatorInfos(organizatorInfoList).build();
    }

}
