package com.portifolyo.eventservice.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.portifolyo.requests.eventservice.enums.EventType;

import java.io.Serializable;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "events",indexes = {
        @Index(name = "idx_name",columnList = "name"),
        @Index(name = "idx_event_date",columnList = "event_date"),
        @Index(name = "idx_event_type",columnList = "event_type")
})
public class Event extends BaseEntity {
    @Column(name = "name")
    private String eventName = "";
    @Column(name = "event_date",nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime eventDate;
    @Column(name = "coming_people",nullable = false)
    private Integer comingPeople = 0;
    @Column(name = "is_ticket")
    private Boolean isTicket = false;
    @Column(name = "is_people_registered",nullable = false)
    private Boolean isPeopleRegistered = false;
    @Enumerated(EnumType.STRING)
    @Column(name = "event_type",nullable = false)
    private EventType eventType;
    @Column(name = "max_people",nullable = false)
    private Integer maxPeople = 0;

    @Column(name = "event_owner",nullable = false,length = 64)
    private String eventOwner;

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private EventDescription eventDescription;
    @OneToMany
    private List<EventArea> eventAreaList = new ArrayList<>();
    public Event(String eventName, LocalDateTime eventDate, Integer comingPeople, Boolean isTicket, Boolean isPeopleRegistered, EventType eventType, int maxPeople, EventDescription eventDescription) {
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
