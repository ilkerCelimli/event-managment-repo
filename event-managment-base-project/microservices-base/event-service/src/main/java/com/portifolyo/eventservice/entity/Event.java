package com.portifolyo.eventservice.entity;

import org.portifolyo.requests.eventservice.enums.EventType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter

public class Event extends BaseEntity {

    @Column(name = "name")
    private String eventName;
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

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private EventDescription eventDescription;


    public Event(String eventName, Date eventDate, Integer comingPeople, Boolean isTicket, Boolean isPeopleRegistered, EventType eventType, int maxPeople, EventDescription eventDescription) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.comingPeople = comingPeople;
        this.isTicket = isTicket;
        this.isPeopleRegistered = isPeopleRegistered;
        this.eventType = eventType;
        this.maxPeople = maxPeople;
        this.eventDescription = eventDescription;
    }
    public Event() {}
}
