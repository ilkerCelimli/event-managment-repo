package com.portifolyo.ticketservice.api;

import com.portifolyo.ticketservice.repository.TicketInfo;
import com.portifolyo.ticketservice.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ticket-service/ticket")
@RestController
@RequiredArgsConstructor
public class TicketApi {

    private final TicketService ticketService;


    @PostMapping("/")
    public ResponseEntity<GenericResponse<Void>> addTicket(@RequestBody @Valid TicketRequest ticketRequest) {
        this.ticketService.handleTicketRequest(ticketRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<TicketRequest>> updateTicket(@PathVariable String id,@RequestBody TicketRequest ticketRequest) {
        TicketRequest t = this.ticketService.updateTicket(id,ticketRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS(t));
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> deleteTicket(@RequestParam String id) {
        this.ticketService.deleteTicket(id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/findTicket/{id}")
    public ResponseEntity<GenericResponse<List<TicketInfo>>> findTickets(@RequestBody TableRequest tableRequest, @PathVariable String id) {
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.ticketService.findTickets(tableRequest,id)));
    }

}
