package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.EventArea;
import org.portifolyo.requests.eventservice.EventAreaRequest;

public class EventAreaMapper {

    public static EventArea toEntity(EventAreaRequest req) {
        return new EventArea(req.area_name(),req.lat(),req.lng(),req.areaCapacity(),req.openAdress());
    }
    private EventAreaMapper(){}

}

