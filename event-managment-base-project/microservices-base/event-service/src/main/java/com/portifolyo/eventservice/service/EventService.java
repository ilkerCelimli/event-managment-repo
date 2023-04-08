package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import org.portifolyo.requests.eventservice.EventSaveRequest;

public interface EventService {

    void saveEventRequestHandle(EventSaveRequest event);

}
