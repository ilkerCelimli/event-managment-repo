package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true,of = {"id"})
public class EventDescription extends BaseEntity {
    @Column(name = "description",nullable = false)
    private String descrtiption;

/*    @OneToMany(mappedBy = "eventDescription",fetch = FetchType.EAGER ,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    private Set<ImageAndLinks> imageAndLinksSet = new HashSet<>();*/

}
