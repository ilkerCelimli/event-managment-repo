package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.projections.OrganizatorEventsInfos;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.EventAndOrganizatorManyToManyService;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.utils.UpdateHelper;
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
        Organizator organizator = this.organizatorRepository.findOrganizatorByEmailEquals(email)
                .orElseThrow(() -> new NotFoundException(String.format("Not found %s",email)));
        return OrganizatorInfo.builder()
                .id(organizator.getId())
                .tcNo(organizator.getEmail())
                .name(organizator.getName())
                .phoneNumber(organizator.getPhoneNumber())
                .surName(organizator.getSurname())
                .mail(organizator.getEmail())
                .build();
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
            return OrganizatorInfo.builder()
                    .id(updated.getId())
                    .tcNo(updated.getTcNo())
                    .name(updated.getName())
                    .surName(updated.getSurname())
                    .mail(updated.getEmail())
                    .phoneNumber(updated.getPhoneNumber()).build();

    }

    @Override
    public void deleteOrganizator(String id) {
        this.organizatorRepository.updateIsDeletedById(true,id);
    }

    @Override
    public OrganizatorEventsInfos findOrganizatorEvents(String email, TableRequest tableRequest) {
        return this.organizatorManyToManyService.complitionOrganizatorEvents(email,tableRequest);
    }
}
