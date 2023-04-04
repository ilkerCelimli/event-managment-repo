package com.portifolyo.eventservice.entity;

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
    @Column
    private Integer comingPeople;
    @Column
    private Boolean isTicket;
    @Column
    private Boolean isPeopleRegistered;

}
