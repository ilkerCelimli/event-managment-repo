package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OrganizatorServiceImpl extends BaseServiceImpl<Organizator> implements OrganizatorService {
    private final OrganizatorRepository organizatorRepository;
    private final String emailRegexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
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
        Optional<OrganizatorInfo> organizatorInfo = this.organizatorRepository.findByEmail(email);
        organizatorInfo.orElseThrow(() -> new NotFoundException(email));
        return organizatorInfo.get();
    }

    @Override
    public Organizator findOrganizatorByEmailEntity(String email) {
       Optional<Organizator> or = this.organizatorRepository.findOrganizatorByEmailEquals(email);
       return or.orElse(null);
    }

    @Override
    public Boolean isExistsOrganizator(String email) {
        return this.organizatorRepository.existsByEmail(email);
    }
}
