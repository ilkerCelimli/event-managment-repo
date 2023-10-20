package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.EventArea;
import com.portifolyo.eventservice.repository.model.EventAreaInfo;

public class EventAreaInfoMapper {


    public static EventAreaInfo toDto(EventArea dto){
        return new EventAreaInfo(dto.getId(),dto.getDeleted(), dto.getAreaName(),dto.getAreaLat(), dto.getAreaLng(),dto.getAreaCapacity(), dto.getOpenAdress());
    }

    private EventAreaInfoMapper(){}
}
