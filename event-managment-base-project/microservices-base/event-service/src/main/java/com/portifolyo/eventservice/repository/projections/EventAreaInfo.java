package com.portifolyo.eventservice.repository.projections;

/**
 * A Projection for the {@link com.portifolyo.eventservice.entity.EventArea} entity
 */
public interface EventAreaInfo {
    String getId();

   // Boolean isDeleted();

    String getAreaName();

    String getAreaLat();

    String getAreaLng();

    Integer getAreaCapacity();

    String getOpenAdress();
}