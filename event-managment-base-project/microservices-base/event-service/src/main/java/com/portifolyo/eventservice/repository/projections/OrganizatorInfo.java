package com.portifolyo.eventservice.repository.projections;

/**
 * A Projection for the {@link com.portifolyo.eventservice.entity.Organizator} entity
 */
public interface OrganizatorInfo {
    String getId();

    String getName();

    String getSurname();

    String getPhoneNumber();

    String getEmail();

    String getTcNo();
}