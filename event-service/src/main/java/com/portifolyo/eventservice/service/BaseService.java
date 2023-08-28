package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.BaseEntity;
import org.portifolyo.requests.TableRequest;

import java.util.List;

public interface BaseService<T extends BaseEntity>{

    T findById(String id);
    T update(T entity);
    T save(T entity);
    List<T> findAll();
    List<T> findAll(TableRequest tableRequest);
}
