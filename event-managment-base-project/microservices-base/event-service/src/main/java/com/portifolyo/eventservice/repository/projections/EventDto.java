package com.portifolyo.eventservice.repository.projections;

import lombok.Builder;
import lombok.Getter;
import org.portifolyo.requests.eventservice.enums.EventType;

import java.util.Date;
import java.util.List;

/**
 * DTO for {@link com.portifolyo.eventservice.entity.Event}
 */
@Getter
@Builder
public class EventDto {
  private  String id;
  private  Date createdDate;
  private  Boolean isDeleted;
  private  String eventName;
  private  Date eventDate;
  private  Integer comingPeople;
  private  Boolean isTicket;
  private  Boolean isPeopleRegistered;
  private  EventType eventType;
  private  int maxPeople;
  private List<OrganizatorInfo> organizatorInfos;
  private EventAreaInfo eventAreaInfo;
  private EventDescriptionInfo eventDescription;

}