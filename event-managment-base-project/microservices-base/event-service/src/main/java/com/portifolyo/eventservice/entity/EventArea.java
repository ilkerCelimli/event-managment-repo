package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true,of = {"id"})
public class EventArea extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id",nullable = false)
    private Event event;

    @Column(name = "area_name",nullable = false)
    private String areaName;

    @Column(name = "area_lat",nullable = false)
    private String areaLat;

    @Column(name = "area_lng",nullable = false)
    private String areaLng;

    @Column(name = "open_adress",nullable = false)
    private String openAdress;

}
