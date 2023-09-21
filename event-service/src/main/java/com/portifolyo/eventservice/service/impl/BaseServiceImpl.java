package com.portifolyo.eventservice.service.impl;

import com.portifolyo.eventservice.entity.BaseEntity;
import com.portifolyo.eventservice.exceptions.NotFoundException;
import com.portifolyo.eventservice.repository.BaseRepository;
import com.portifolyo.eventservice.service.BaseService;
import lombok.extern.log4j.Log4j2;
import org.portifolyo.requests.TableRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.List;

@Log4j2
public class BaseServiceImpl<T extends BaseEntity> implements BaseService<T> {

    protected final BaseRepository<T> baseRepository;

    public BaseServiceImpl(BaseRepository<T> baseRepository) {
        this.baseRepository = baseRepository;
    }

    public T save(T entity) {
        T saved = this.baseRepository.save(entity);
        log.info("saved id {}",saved.getId());
        return saved;
    }

    public T update(T entity) {
        T updated = this.baseRepository.save(entity);
        log.info("updated id {}",updated.getId());
        return updated;
    }

    public T findById(String id) {
        return this.baseRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public List<T> findAll() {
        return this.baseRepository.findAll();
    }

    @Override
    public List<T> findAll(TableRequest tableRequest) {
        return this.baseRepository.findAll(PageRequest.of(tableRequest.getPage(),tableRequest.getSize())).toList();
    }

    protected void delete(String id) {
        T entity = findById(id);
        entity.setDeleted(true);
        this.baseRepository.save(entity);
    }

}
