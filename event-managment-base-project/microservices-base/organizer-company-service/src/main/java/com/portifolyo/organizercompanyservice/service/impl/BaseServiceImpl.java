package com.portifolyo.organizercompanyservice.service.impl;

import com.portifolyo.organizercompanyservice.entity.BaseEntity;
import com.portifolyo.organizercompanyservice.repository.BaseRepository;
import com.portifolyo.organizercompanyservice.service.BaseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;
@RequiredArgsConstructor
@Slf4j
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    private final BaseRepository<T> baseRepository;

    @Override
    public T save(T entity) {
        entity.setCreatedDate(LocalDateTime.now());
        entity.setUpdatedDate(LocalDateTime.now());
        T saved = baseRepository.save(entity);
        if(saved.getId() != null){
            log.info("saved {} , {} date",entity.getId(),saved.getCreatedDate());
            return saved;
        }
        //TODO refactor this log
        log.error("error{} date",entity.getCreatedDate());
        //TODO refactor this exception
        throw new RuntimeException();
    }

    @Override
    public T update(T entity) {
        entity.setUpdatedDate(LocalDateTime.now());
        return entity;
    }

    @Override
    public void delete(T entity) {
        entity.setActive(false);
        this.baseRepository.save(update(entity));
        log.info("deleted {} id {} date",entity.getId(),entity.getUpdatedDate());
    }

    @Override
    public List<T> findAll() {
        return baseRepository.findAll(Sort.by("id"));
    }

    @Override
    public T findById(String id) {
        // TODO refactor this exception
        return this.baseRepository.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
