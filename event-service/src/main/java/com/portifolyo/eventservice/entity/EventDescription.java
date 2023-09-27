package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventDescription extends BaseEntity implements Serializable {
    @Column(name = "description",nullable = false)
    private String descrtiption;

   @OneToMany(mappedBy = "eventDescription",fetch = FetchType.EAGER ,cascade = CascadeType.ALL)
    private Set<ImageAndLinks> imageAndLinksSet;


   @Override
    public String toString(){
       return String.format("id: %s",super.getId());
   }

}
