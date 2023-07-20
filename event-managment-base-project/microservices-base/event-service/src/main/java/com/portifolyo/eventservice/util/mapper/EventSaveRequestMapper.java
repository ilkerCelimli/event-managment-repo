package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventDescription;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.requests.eventservice.ImageAndLinksReqeust;

import java.util.HashSet;
import java.util.Set;

public class EventSaveRequestMapper {

    private EventSaveRequestMapper(){}

    public static Event toEntity(EventSaveRequest request){
        Event ref = new Event();
        ref.setEventDate(request.eventDate());
        ref.setEventType(request.eventType());
        ref.setComingPeople(request.comingPeople());
        ref.setIsPeopleRegistered(request.isPeopleIsRegistered());
        ref.setIsTicket(request.isTicket());
        ref.setEventName(request.eventName());
        EventDescription eventDescription = new EventDescription();
        eventDescription.setDescrtiption(request.description().description());
        Set<ImageAndLinks> images = new HashSet<>();
        for(ImageAndLinksReqeust req : request.description().imageAndLinksReqeusts()){
            ImageAndLinks lins = new ImageAndLinks();
            lins.setItem(req.item());
            lins.setDescriptionTypes(req.descriptionTypes());
            images.add(lins);
        }
        eventDescription.setImageAndLinksSet(images);
        ref.setEventDescription(eventDescription);

        return ref;
    }

}
