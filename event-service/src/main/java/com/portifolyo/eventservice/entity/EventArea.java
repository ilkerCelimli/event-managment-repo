package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(indexes = {
        @Index(name = "idx_area_name",columnList = "area_name"),
        @Index(name = "idx_coordinates",columnList = "area_lat,area_lng")
})
public class EventArea extends BaseEntity implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",nullable = false)
    private Event event;

    @Column(name = "area_name",nullable = false)
    private String areaName;

    @Column(name = "area_lat",nullable = false)
    private String areaLat;

    @Column(name = "area_lng",nullable = false)
    private String areaLng;
    @Column(name ="area_capacity",nullable = false)
    private Integer areaCapacity = 0;

    @Column(name = "open_adress",nullable = false)
    private String openAdress;

    public EventArea(String areaName, String areaLat, String areaLng, Integer areaCapacity, String openAdress) {
        this.areaName = areaName;
        this.areaLat = areaLat;
        this.areaLng = areaLng;
        this.areaCapacity = areaCapacity;
        this.openAdress = openAdress;
    }
    public EventArea() {}


    @Override
    public String toString(){
        return String.format("id: %s",super.getId());
    }
}
