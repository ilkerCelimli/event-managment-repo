package com.portifolyo.eventservice.util.mapper;

import com.portifolyo.eventservice.entity.Organizator;
import org.portifolyo.requests.eventservice.OrganizatorRequest;

import java.util.ArrayList;
import java.util.List;

public class OrganizatorRequestMapper {


    public static Organizator toEntity(OrganizatorRequest req) {

       return new Organizator(req.name(),req.surname(),req.phoneNumber(),req.email(),req.tcNo());

    }
}
