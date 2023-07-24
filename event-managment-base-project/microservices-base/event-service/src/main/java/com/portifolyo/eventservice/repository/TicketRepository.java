package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.Ticket;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TicketRepository extends BaseRepository<Ticket> {

    @Query("select t from Ticket t where t.event.id = ?1 and t.deleted = false order by t.createdDate")
    List<TicketInfo> findTicketsInfo(String id, Pageable pageable);


}
