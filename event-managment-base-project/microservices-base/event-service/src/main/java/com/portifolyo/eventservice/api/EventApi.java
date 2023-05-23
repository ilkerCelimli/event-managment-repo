package com.portifolyo.eventservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.eventservice.repository.projections.EventInfo;
import com.portifolyo.eventservice.service.EventService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping("/")
    public ResponseEntity<GenericResponse<Void>> updateEvent(@JsonInclude(JsonInclude.Include.NON_NULL)
                                                                 @RequestBody
                                                                 EventSaveRequest request,@RequestParam String id){
        this.eventService.updateEventRequestHandle(request,id);
        return ResponseEntity.ok(new GenericResponse<>(200,"Success"));
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> inActiveEvent(@RequestParam String id){
        this.eventService.eventInActiveHandle(id);
        return ResponseEntity.ok(new GenericResponse<Void>(200,"deleted"));
    }


    @GetMapping("/findEventsByOrganizator")
    public ResponseEntity<GenericResponse<List<EventInfo>>> findEventsByOrganizator(@RequestParam String organizatorId){
        List<EventInfo> eventInfo = this.eventService.findEventsByOrganizatorMail(organizatorId);
        return ResponseEntity.ok(new GenericResponse<>(200,"Success",eventInfo));

    }

    

}
