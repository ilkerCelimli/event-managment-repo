package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.*;


@Inheritance(strategy = InheritanceType.JOINED)
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EventAndOrganizatorManyToMany extends BaseEntity {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "organizator_id")
    private Organizator organizator;

    @Override
    public String toString(){
        return this.getId();
    }
}
