package com.portifolyo.eventservice.repository.projections;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.portifolyo.requests.eventservice.enums.EventType;

import java.time.LocalDateTime;
import java.util.List;

/**
 * DTO for {@link com.portifolyo.eventservice.entity.Event}
 */
@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventDto {
    private String id;
    private LocalDateTime createdDate;
    private Boolean isDeleted;
    private String eventName;
    private LocalDateTime eventDate;
    private Integer comingPeople;
    private Boolean isTicket;
    private Boolean isPeopleRegistered;
    private EventType eventType;
    private int maxPeople;
    private List<OrganizatorInfo> organizatorInfos;
    private EventAreaInfo eventAreaInfo;
    private EventDescriptionInfo eventDescription;

}