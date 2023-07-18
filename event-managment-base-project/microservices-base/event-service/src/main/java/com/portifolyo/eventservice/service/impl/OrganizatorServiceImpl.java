package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.springframework.stereotype.Service;

@Service
public class OrganizatorServiceImpl extends BaseServiceImpl<Organizator> implements OrganizatorService {

    final OrganizatorRepository organizatorRepository;
    final EventAndOrganizatorManyToManyService organizatorManyToManyService;

    public OrganizatorServiceImpl(OrganizatorRepository organizatorRepository, EventAndOrganizatorManyToManyService organizatorManyToManyService){
        super(organizatorRepository);
        this.organizatorRepository = organizatorRepository;
        this.organizatorManyToManyService = organizatorManyToManyService;
    }

    @Override
    public Organizator handleOrganizator(OrganizatorRequest organizatorRequest) {
        return save(OrganizatorRequestMapper.toEntity(organizatorRequest));
    }

    @Override
    public OrganizatorInfo findOrganizatorByEmail(String email) {
        return null;
    }

    @Override
    public Organizator findOrganizatorByEmailEntity(String email) {
        return null;
    }

    @Override
    public OrganizatorInfo updateOrganizator(OrganizatorRequest or, String id) {
        return null;
    }

    @Override
    public void deleteOrganizator(String id) {
            //TODO not implemented
    }
}
