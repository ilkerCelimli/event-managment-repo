package com.portifolyo.eventservice.entity;

import lombok.*;
import org.portifolyo.requests.eventservice.enums.DescriptionTypes;
import org.portifolyo.requests.eventservice.enums.EventType;
import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@ToString(of = "id")
@EqualsAndHashCode(callSuper = true,of = {"id"})
@AllArgsConstructor
@NoArgsConstructor
public class ImageAndLinks extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_description_id",nullable = false)
    private EventDescription eventDescription;
    @Column(name = "item",nullable = false)
    private String item;

    @Column(name = "type",nullable = false)
    @Enumerated(EnumType.STRING)
    private DescriptionTypes descriptionTypes;




}
