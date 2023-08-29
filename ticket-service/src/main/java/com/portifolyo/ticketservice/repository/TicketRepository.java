package com.portifolyo.ticketservice.repository;

import com.portifolyo.ticketservice.entity.Ticket;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<Ticket,String> {
    List<TicketInfo> findByDeletedFalseAndEvent_IdOrderByCreatedDateAsc(String id, Pageable pageable);


}
