package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(indexes = {
        @Index(name = "idx_user_email",columnList = "user_email")
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class IncomingPeople extends BaseEntity {

    @Column(name = "user_email")
    private String userEmail;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "incomingPeople")
    private List<EventAndIncomingPeopleManyToMany> incomingPeopleManyToManyList;

}
