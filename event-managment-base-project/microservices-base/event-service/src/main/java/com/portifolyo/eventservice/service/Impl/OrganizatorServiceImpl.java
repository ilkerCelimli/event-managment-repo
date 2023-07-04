package com.portifolyo.eventservice.service.Impl;

import com.portifolyo.eventservice.entity.Organizator;
import com.portifolyo.eventservice.exceptions.GenericException;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.OrganizatorRepository;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.BaseServiceImpl;
import com.portifolyo.eventservice.service.OrganizatorService;
import com.portifolyo.eventservice.util.mapper.OrganizatorRequestMapper;
import jakarta.transaction.Transactional;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.utils.UpdateHelper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
public class OrganizatorServiceImpl extends BaseServiceImpl<Organizator> implements OrganizatorService {
    private final OrganizatorRepository organizatorRepository;
    private final RabbitTemplate rabbitTemplate;
    private final String emailRegexPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    public OrganizatorServiceImpl(OrganizatorRepository organizatorRepository, RabbitTemplate rabbitTemplate) {
        super(organizatorRepository);
        this.organizatorRepository = organizatorRepository;
        this.rabbitTemplate = rabbitTemplate;
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
    @Transactional
    public OrganizatorInfo updateOrganizator(OrganizatorRequest or, String id) {
        Optional<Organizator> o = this.organizatorRepository.findById(id);
        o.orElseThrow(() -> new NotFoundException(id));
/*        if(or.email() != null) o.get().setEmail(or.email());
        if(or.name() != null) o.get().setName(or.name());
        if(or.surname() != null) o.get().setSurname(or.surname());
        if(or.tcNo() != null) o.get().setTcNo(or.tcNo());
        if(or.phoneNumber() != null) o.get().setPhoneNumber(or.phoneNumber());*/
        UpdateHelper<OrganizatorRequest,Organizator> updateHelper = new UpdateHelper<>();
        try {
           Organizator updated = updateHelper.updateHelper(or,o.get());
           save(updated);
           return this.findOrganizatorByEmail(updated.getEmail());
        }
        catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException ex) {
            System.out.println(ex);
        }
        return null;
    }

    @Override
    public void deleteOrganizator(String id) {
        organizatorRepository.updateIsDeletedById(true,id);

    }


}
