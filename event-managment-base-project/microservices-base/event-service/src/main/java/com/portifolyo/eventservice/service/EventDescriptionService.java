package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.EventDescription;
import org.portifolyo.requests.eventservice.EventDescriptionRequest;

import java.util.List;

public interface EventDescriptionService {

    EventDescription eventDescriptionHandler(EventDescriptionRequest list);
}
