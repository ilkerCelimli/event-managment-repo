package com.portifolyo.eventservice.feign;

import org.portifolyo.requests.eventservice.TicketRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("TICKET-SERVICE")
public interface TicketServiceFeignClient {

    @PostMapping("/ticket-service/ticket")
     ResponseEntity<GenericResponse<Void>> saveTicket(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody TicketRequest ticketRequest);
}
