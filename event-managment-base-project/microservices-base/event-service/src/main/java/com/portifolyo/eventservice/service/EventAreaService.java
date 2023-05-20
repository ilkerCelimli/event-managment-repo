package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventArea;
import org.portifolyo.requests.eventservice.EventAreaRequest;

public interface EventAreaService {


     void handleEventAreaRequest(EventAreaRequest areaRequest, Event e);

}
