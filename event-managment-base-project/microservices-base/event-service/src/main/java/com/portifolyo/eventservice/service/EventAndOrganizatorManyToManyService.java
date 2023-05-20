package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.EventAndOrganizatorManyToMany;
import com.portifolyo.eventservice.entity.Organizator;
import org.portifolyo.requests.eventservice.OrganizatorRequest;

import java.util.List;

public interface EventAndOrganizatorManyToManyService {

    void saveOrganizator(List<OrganizatorRequest> o, Event e);

}
