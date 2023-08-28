package com.portifolyo.organizercompanyservice.repository;

import com.portifolyo.organizercompanyservice.entity.Place;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PlaceRepository extends BaseRepository<Place>{
    @Transactional
    @Modifying
    @Query("update PLACES p set p.isBusy = ?1 where p.id = ?2")
    void updatePlaceBusyStatus(boolean isBusy, String id);

    @Query("select p from PLACES p where p.isBusy = false and p.company.id = ?1 order by p.id")
    List<PlaceInfo> findPlacesByCompanyId(String id, Pageable pageable);

}
