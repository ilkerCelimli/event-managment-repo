package com.portifolyo.eventservice.repository;

import com.portifolyo.eventservice.entity.Ticket;
import com.portifolyo.eventservice.repository.projections.TicketInfo;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TicketRepository extends BaseRepository<Ticket> {
    List<TicketInfo> findByDeletedFalseAndEvent_IdOrderByCreatedDateAsc(String id, Pageable pageable);


}
