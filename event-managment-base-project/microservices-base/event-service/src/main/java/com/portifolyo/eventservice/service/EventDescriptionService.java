package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import org.portifolyo.requests.eventservice.EventDescriptionRequest;

import java.util.List;

public interface EventDescriptionService {

    EventDescription eventDescriptionHandler(EventDescriptionRequest list);
    void addImages(Event event, List<ImageAndLinks> images);
}
