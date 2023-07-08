package com.portifolyo.eventservice.service;

import com.portifolyo.eventservice.entity.BaseEntity;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.BaseRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Optional;

@Log4j2
public class BaseServiceImpl<T extends BaseEntity> {

    protected final BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    protected T save(T entity) {
        T saved = this.baseRepository.save(entity);
        log.info("saved id {}",saved.getId());
        return saved;
    }

    protected T update(T entity) {
        T updated = this.baseRepository.save(entity);
        log.info("updated id {}",updated.getId());
        return updated;
    }

    protected T findById(String id) {
        return this.baseRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    protected List<T> findAll() {
        return this.baseRepository.findAll();
    }

    protected void delete(String id) {
        T entity = findById(id);
        entity.setIsDeleted(true);
        this.baseRepository.save(entity);
    }

}
