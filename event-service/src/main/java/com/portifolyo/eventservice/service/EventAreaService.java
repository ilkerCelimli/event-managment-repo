package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventArea;
import com.portifolyo.eventservice.repository.model.EventAreaInfo;
import org.portifolyo.requests.eventservice.EventAreaRequest;

public interface EventAreaService extends BaseService<EventArea> {


     void handleEventAreaRequest(EventAreaRequest areaRequest, Event e);
     EventAreaInfo findEventArea(String eventId);

}
