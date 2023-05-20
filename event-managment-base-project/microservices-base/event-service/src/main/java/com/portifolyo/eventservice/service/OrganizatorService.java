package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.Organizator;
import org.portifolyo.requests.eventservice.OrganizatorRequest;

public interface OrganizatorService {

    Organizator handleOrganizator(OrganizatorRequest organizatorRequest);
}
