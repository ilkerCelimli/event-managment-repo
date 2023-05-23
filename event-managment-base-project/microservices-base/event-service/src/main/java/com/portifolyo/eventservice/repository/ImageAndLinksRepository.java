package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.ImageAndLinks;

import java.util.List;
import java.util.Set;

public interface ImageAndLinksRepository extends BaseRepository<ImageAndLinks>{
    Set<ImageAndLinks> findByEventDescription_Id(String id);
}
