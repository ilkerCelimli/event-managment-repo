package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.model.OrganizatorEventsInfos;
import com.portifolyo.eventservice.repository.model.OrganizatorInfo;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorInfoMapper;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.portifolyo.commonexceptions.NotFoundException;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(cacheNames = "organizator",key = "#organizatorRequest.email()")
    public Organizator handleOrganizator(OrganizatorRequest organizatorRequest) {
        return save(OrganizatorRequestMapper.toEntity(organizatorRequest));
    }

    @Override
    @Cacheable(cacheNames = "organizator",key = "#email")
    public OrganizatorInfo findOrganizatorByEmail(String email) {
        Organizator organizator = this.organizatorRepository.findOrganizatorByEmailEquals(email)
                .orElseThrow(() -> new NotFoundException(String.format("Not found %s",email)));
        return OrganizatorInfoMapper.toDto(organizator);
    }

    @Override
    public Organizator findOrganizatorByEmailEntity(String email) {
        return this.organizatorRepository.findOrganizatorByEmailEquals(email)
                .orElseThrow(() -> new NotFoundException(String.format("Not found %s",email)));
    }

    @Override
    public OrganizatorInfo updateOrganizator(OrganizatorRequest or, String id) {
            UpdateHelper<OrganizatorRequest,Organizator> updateHelper = new UpdateHelper<>();
            Organizator organizator = this.organizatorRepository.findById(id).orElseThrow(() -> new NotFoundException(
                    String.format("%s id not found",id)
            ));
            Organizator updated = updateHelper.updateHelper(or,organizator);
            if(updated == null) throw new GenericException("update problem",500);
            save(updated);
            return OrganizatorInfoMapper.toDto(organizator);

    }

    @Override
    public void deleteOrganizator(String id) {
        this.organizatorRepository.updateIsDeletedById(true,id);
    }

    @Override
    @Cacheable(cacheNames = "organizator_events",key = "#email")
    public OrganizatorEventsInfos findOrganizatorEvents(String email, TableRequest tableRequest) {
        return this.organizatorManyToManyService.complitionOrganizatorEvents(email,tableRequest);
    }
}
