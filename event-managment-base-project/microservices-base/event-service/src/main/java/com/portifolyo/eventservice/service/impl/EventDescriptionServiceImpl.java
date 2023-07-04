package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.repository.EventDescriptionRepository;
import com.portifolyo.eventservice.repository.ImageAndLinksRepository;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventDescriptionService;
import com.portifolyo.eventservice.util.mapper.EventDescriptionMapper;
import org.portifolyo.requests.eventservice.EventDescriptionRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventDescriptionServiceImpl extends BaseServiceImpl<EventDescription> implements EventDescriptionService {
    private final ImageAndLinksRepository imageAndLinksService;

    public EventDescriptionServiceImpl(EventDescriptionRepository eventDescriptionRepository, ImageAndLinksRepository imageAndLinksService) {
        super(eventDescriptionRepository);
        this.imageAndLinksService = imageAndLinksService;
    }

    @Override
    public EventDescription eventDescriptionHandler(EventDescriptionRequest list) {
        EventDescription eventDescription = EventDescriptionMapper.toEntity(list);
        EventDescription e = save(eventDescription);
        List<ImageAndLinks> images = new ArrayList<>();
        list.imageAndLinksReqeusts().forEach(i -> images.add(new ImageAndLinks(e,i.item(),i.descriptionTypes())));
        this.imageAndLinksService.saveAll(images);

        return e;
    }

    @Override
    public void addImages(Event event, List<ImageAndLinks> images) {
        EventDescription desc = event.getEventDescription();
        images.forEach(i -> i.setEventDescription(desc));
        this.imageAndLinksService.saveAll(images);
    }
}
