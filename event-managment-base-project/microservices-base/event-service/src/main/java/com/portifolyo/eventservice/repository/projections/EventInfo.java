package com.portifolyo.eventservice.repository.projections;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portifolyo.requests.eventservice.enums.EventType;

import java.util.Date;
import java.util.List;

/**
 * A Projection for the {@link com.portifolyo.eventservice.entity.Event} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventInfo {
   private String id;

   private String name;

   private Date eventDate;

   private Integer comingPeople;

   private Boolean isTicket;

   private Boolean isPeopleRegistered;

   private EventType eventType;

   private int maxPeople;

   private EventDescriptionInfo eventDescription;
   private EventAreaInfo eventAreaInfo;
   private List<OrganizatorInfo> organizatorInfos;

    /**
     * A Projection for the {@link com.portifolyo.eventservice.entity.EventDescription} entity
     */

}