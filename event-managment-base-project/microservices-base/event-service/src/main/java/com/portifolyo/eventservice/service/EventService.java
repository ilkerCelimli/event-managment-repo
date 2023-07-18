package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.repository.projections.EventDto;
import jakarta.persistence.Table;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.EventSaveRequest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface EventService {

    void saveEventRequestHandle(EventSaveRequest event);
    void updateEventRequestHandle(EventSaveRequest event,String eventId) throws InvocationTargetException, IllegalAccessException, NoSuchMethodException;

    void eventInActiveHandle(String eventId);

    void addimages(String eventid, List<ImageAndLinks> imageAndLinks);

    List<EventDto> findEvents(TableRequest request);

}
