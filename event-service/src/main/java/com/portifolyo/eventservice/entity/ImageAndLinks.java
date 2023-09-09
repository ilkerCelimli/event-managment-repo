package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.portifolyo.requests.eventservice.enums.DescriptionTypes;

import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ImageAndLinks extends BaseEntity implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "event_description_id")
    private EventDescription eventDescription;
    @Column(name = "item",nullable = false,length = 4000)
    private String item;

    @Column(name = "type",nullable = false,length = 16)
    @Enumerated(EnumType.STRING)
    private DescriptionTypes descriptionTypes;


    @Override
    public String toString(){
        return String.format("id: %s",super.getId());
    }

}
