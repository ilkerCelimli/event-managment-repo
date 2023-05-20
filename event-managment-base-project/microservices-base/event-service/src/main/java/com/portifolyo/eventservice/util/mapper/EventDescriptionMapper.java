package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import org.portifolyo.requests.eventservice.EventDescriptionRequest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EventDescriptionMapper {

    public static EventDescription toEntity(EventDescriptionRequest req) {
        EventDescription e = new EventDescription();
        e.setDescrtiption(req.description());
        Set<ImageAndLinks> images = new HashSet<>();
        req.imageAndLinksReqeusts().stream().forEach(i -> {
            ImageAndLinks links = new ImageAndLinks();
            links.setItem(i.item());
            links.setDescriptionTypes(i.descriptionTypes());
         //   links.setIsDeleted(false);
            images.add(links);
        });
        e.setImageAndLinksSet(images);

        return e;
    }
}
