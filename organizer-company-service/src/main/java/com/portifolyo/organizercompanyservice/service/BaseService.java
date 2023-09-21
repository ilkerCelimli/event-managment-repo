package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.BaseEntity;

import java.util.List;

public interface BaseService<T extends BaseEntity> {

    T save(T entity);
    T update(T entity);
    void delete(T entity);
    List<T> findAll();
    T findById(String id);

}
