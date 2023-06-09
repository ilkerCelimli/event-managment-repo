package com.portifolyo.eventservice.api;

import com.portifolyo.eventservice.repository.projections.TicketInfo;
import com.portifolyo.eventservice.service.TicketService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/ticket")
@RestController
@RequiredArgsConstructor
public class TicketApi {

    private final TicketService ticketService;


    @PostMapping("/")
    public ResponseEntity<GenericResponse<Void>> addTicket(@RequestBody @Valid TicketRequest ticketRequest) {
        this.ticketService.handleTicketRequest(ticketRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @PutMapping("/")
    public ResponseEntity<GenericResponse<TicketRequest>> updateTicket(@RequestBody TicketRequest ticketRequest) {
        TicketRequest t = this.ticketService.updateTicket(ticketRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS(t));
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> deleteTicket(@RequestParam String id) {
        this.ticketService.deleteTicket(id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/findTickets")
    public ResponseEntity<GenericResponse<List<TicketInfo>>> findTickets(@RequestParam Integer size, @RequestParam Integer page, @RequestParam String eventId) {
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.ticketService.findTickets(page, size, eventId)));
    }

}
