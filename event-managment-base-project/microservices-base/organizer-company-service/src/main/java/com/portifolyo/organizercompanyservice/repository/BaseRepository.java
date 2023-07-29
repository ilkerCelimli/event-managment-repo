package com.portifolyo.organizercompanyservice.repository;

import com.portifolyo.organizercompanyservice.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseRepository<T extends BaseEntity> extends JpaRepository<T,String> {
}
