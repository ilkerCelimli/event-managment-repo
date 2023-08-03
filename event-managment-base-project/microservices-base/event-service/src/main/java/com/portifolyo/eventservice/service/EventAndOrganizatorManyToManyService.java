package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.repository.projections.OrganizatorEventsInfos;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;

import java.util.List;

public interface EventAndOrganizatorManyToManyService {

    void saveOrganizator(List<OrganizatorRequest> o, Event e);
    List<OrganizatorInfo> findOrganizatorsByEventId(String id);

    OrganizatorEventsInfos complitionOrganizatorEvents(String email, TableRequest tableRequest);
}
