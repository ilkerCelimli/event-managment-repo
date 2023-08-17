package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.repository.projections.EventAreaInfo;
import org.portifolyo.requests.eventservice.EventAreaRequest;

public interface EventAreaService {


     void handleEventAreaRequest(EventAreaRequest areaRequest, Event e);
     EventAreaInfo findEventArea(String eventId);

}
