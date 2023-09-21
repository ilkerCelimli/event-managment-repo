package com.portifolyo.eventservice.repository.projections;

import com.portifolyo.eventservice.entity.Organizator;
import lombok.*;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class OrganizatorEventsInfos {

    private String id;

    private String name;

    private String surName;

    private String phoneNumber;

    private String mail;

    private String tcNo;
    private List<EventDto> eventList;

    public static OrganizatorEventsInfos buildOrganizatorEntity(Organizator request){
        return OrganizatorEventsInfos.builder()
                .id(request.getId())
                .name(request.getName())
                .surName(request.getSurname())
                .phoneNumber(request.getPhoneNumber())
                .mail(request.getEmail())
                .tcNo(request.getTcNo())
                .build();
    }

}
