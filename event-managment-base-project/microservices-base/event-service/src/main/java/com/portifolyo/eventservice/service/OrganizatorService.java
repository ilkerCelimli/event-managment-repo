package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import org.portifolyo.requests.eventservice.OrganizatorRequest;

public interface OrganizatorService {

    Organizator handleOrganizator(OrganizatorRequest organizatorRequest);

    OrganizatorInfo findOrganizatorByEmail(String email);
    Organizator findOrganizatorByEmailEntity(String email);
    OrganizatorInfo updateOrganizator(OrganizatorRequest or,String id);

    void deleteOrganizator(String id);
}
