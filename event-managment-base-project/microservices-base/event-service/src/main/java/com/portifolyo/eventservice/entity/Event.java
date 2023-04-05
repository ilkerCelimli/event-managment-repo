package com.portifolyo.eventservice.entity;

import com.portifolyo.eventservice.enums.EventType;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Event extends BaseEntity {

    @Column
    private String name;
    @Column(name = "event_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date eventDate;
    @Column(name = "coming_people")
    private Integer comingPeople;
    @Column(name = "is_ticket")
    private Boolean isTicket;
    @Column(name = "is_people_registered")
    private Boolean isPeopleRegistered;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type",nullable = false)
    private EventType eventType;
    @Column(name = "max_people",nullable = false)
    private int maxPeople;
}
