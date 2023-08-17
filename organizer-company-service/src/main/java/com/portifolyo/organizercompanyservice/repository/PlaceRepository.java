package com.portifolyo.organizercompanyservice.repository;

import com.portifolyo.organizercompanyservice.entity.Place;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PlaceRepository extends BaseRepository<Place>{
    @Transactional
    @Modifying
    @Query("update PLACES p set p.isBusy = ?1 where p.id = ?2")
    void updatePlaceBusyStatus(boolean isBusy, String id);
}
