package com.portifolyo.eventservice.repository.projections;

import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

/**
 * A Projection for the {@link com.portifolyo.eventservice.entity.Organizator} entity
 */
@Getter
@Builder
public class OrganizatorInfo implements Serializable {
    String id;

    String name;

    String surName;

    String phoneNumber;

    String mail;

    String tcNo;
}