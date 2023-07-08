package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
@Slf4j
public class OrganizatorServiceImpl extends BaseServiceImpl<Organizator> implements OrganizatorService {
    private final OrganizatorRepository organizatorRepository;
    private static String emailRegexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public OrganizatorServiceImpl(OrganizatorRepository organizatorRepository) {
        super(organizatorRepository);
        this.organizatorRepository = organizatorRepository;
    }

    @Override
    public Organizator handleOrganizator(OrganizatorRequest organizatorRequest) {
        if(!organizatorRequest.email().matches(emailRegexPattern)) throw new GenericException("Email pattern not matched",404);
       return save(OrganizatorRequestMapper.toEntity(organizatorRequest));
    }



    @Override
    public OrganizatorInfo findOrganizatorByEmail(String email) {
        return this.organizatorRepository.findByEmail(email).orElseThrow(() -> new NotFoundException(email));
    }

    @Override
    public Organizator findOrganizatorByEmailEntity(String email) {
       Optional<Organizator> or = this.organizatorRepository.findOrganizatorByEmailEquals(email);
       return or.orElse(null);
    }

    @Override
    @Transactional
    public OrganizatorInfo updateOrganizator(OrganizatorRequest or, String id) {
       Organizator o = this.organizatorRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
        UpdateHelper<OrganizatorRequest,Organizator> updateHelper = new UpdateHelper<>();
        try {
           Organizator updated = updateHelper.updateHelper(or,o);
           save(updated);
           return this.findOrganizatorByEmail(updated.getEmail());
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

    @Override
    public void deleteOrganizator(String id) {
        organizatorRepository.updateIsDeletedById(true,id);

    }


}
