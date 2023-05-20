package com.portifolyo.eventservice.api;

import com.portifolyo.eventservice.feign.UserServiceFeignClient;
import com.portifolyo.eventservice.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.requests.userservice.UserInfo;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/event")
@RequiredArgsConstructor
public class EventApi {

    private final EventService eventService;

    @PostMapping("/")
    public ResponseEntity<GenericResponse<Void>> saveEvent(@Valid @RequestBody EventSaveRequest request) {
        this.eventService.saveEventRequestHandle(request);
        return ResponseEntity.ok(new GenericResponse<>(200,"Success"));
    }

}
