package com.portifolyo.eventservice.entity;

import org.portifolyo.requests.eventservice.enums.EventType;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true,of = {"id"})
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

    @OneToOne(fetch = FetchType.EAGER)
    private EventDescription eventDescription;

}
