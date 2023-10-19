package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;

public class OrganizatorInfoMapper {

    public static OrganizatorInfo toDto(Organizator organizator){
        return OrganizatorInfo.builder()
                .id(organizator.getId())
                .tcNo(organizator.getEmail())
                .name(organizator.getName())
                .phoneNumber(organizator.getPhoneNumber())
                .surName(organizator.getSurname())
                .mail(organizator.getEmail())
                .build();
    }

    public OrganizatorInfoMapper(){}
}
