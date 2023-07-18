package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.repository.projections.EventDescriptionInfo;
import org.portifolyo.requests.eventservice.EventDescriptionRequest;
import org.portifolyo.requests.eventservice.ImageAndLinksReqeust;

import java.util.HashSet;
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
            images.add(links);
        });
        return e;
    }

    public static EventDescriptionInfo toDto(EventDescription req){
        EventDescriptionInfo eventDescriptionInfo = new EventDescriptionInfo();
        eventDescriptionInfo.setDescription(req.getDescrtiption());
        Set<ImageAndLinksReqeust> set = new HashSet<>();
        for(ImageAndLinks links : req.getImageAndLinksSet()){
            ImageAndLinksReqeust r = new ImageAndLinksReqeust(links.getItem(),links.getDescriptionTypes());
            set.add(r);
        }
        eventDescriptionInfo.setCreatedDate(req.getCreatedDate());
        eventDescriptionInfo.setImageAndLinksList(set);
        eventDescriptionInfo.setIsDeleted(req.getIsDeleted());
        eventDescriptionInfo.setId(req.getId());
        return eventDescriptionInfo;
    }

    private EventDescriptionMapper() {
    }
}
