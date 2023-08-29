package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "incoming_people")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class IncomingPeople extends BaseEntity {

    @Column(name = "user_id")
    private String userId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<EventAndIncomingPeopleManyToMany> incomingPeopleManyToManyList = new ArrayList<>();

}
