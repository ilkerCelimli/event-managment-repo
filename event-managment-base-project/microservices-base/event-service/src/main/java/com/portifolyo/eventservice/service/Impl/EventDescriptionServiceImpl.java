package com.portifolyo.eventservice.service.Impl;

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
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
     /*
        Set<ImageAndLinks> images = eventDescription.getImageAndLinksSet();
        eventDescription.setImageAndLinksSet(null);*/
        EventDescription e = save(eventDescription);
        List<ImageAndLinks> images = new ArrayList<>();
        list.imageAndLinksReqeusts().forEach(i -> images.add(new ImageAndLinks(e,i.item(),i.descriptionTypes())));
        this.imageAndLinksService.saveAll(images);
        return e;
    }
}
