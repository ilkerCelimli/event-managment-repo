package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T,String> {
}
