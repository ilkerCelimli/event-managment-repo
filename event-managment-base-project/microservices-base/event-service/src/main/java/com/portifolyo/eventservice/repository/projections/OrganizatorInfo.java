package com.portifolyo.eventservice.repository.projections;

import lombok.Builder;
import lombok.Getter;

/**
 * A Projection for the {@link com.portifolyo.eventservice.entity.Organizator} entity
 */
@Getter
@Builder
public class OrganizatorInfo {
    String id;

    String name;

    String surName;

    String phoneNumber;

    String mail;

    String tcNo;
}