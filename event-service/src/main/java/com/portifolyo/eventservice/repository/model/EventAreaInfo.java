package com.portifolyo.eventservice.repository.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * A Projection for the {@link com.portifolyo.eventservice.entity.EventArea} entity
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventAreaInfo implements Serializable {

    private String id;
    private boolean isDeleted;
    private String areaName;

    private String areaLat;
    private String areaLng;
    private int areaCapacity;
    private String getOpenAdress;

}