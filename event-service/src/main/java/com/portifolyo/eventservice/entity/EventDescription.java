package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Table(name = "event_description")
public class EventDescription extends BaseEntity implements Serializable {
    @Column(name = "description",nullable = false)
    private String descrtiption;

   @OneToMany(mappedBy = "eventDescription",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private Set<ImageAndLinks> imageAndLinksSet = new HashSet<>();


   @Override
    public String toString(){
       return String.format("id: %s",super.getId());
   }

}
