package com.portifolyo.eventservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.eventservice.entity.Event;
import com.portifolyo.eventservice.entity.ImageAndLinks;
import com.portifolyo.eventservice.repository.projections.EventDto;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.util.mapper.EventSaveRequestMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.annotation.security.RolesAllowed;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.EventRegisterRequest;
import org.portifolyo.requests.eventservice.EventSaveRequest;
import org.portifolyo.response.GenericResponse;
import org.portifolyo.utils.JsonTokenUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event-service/event")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")
public class EventApi {

    private final EventService eventService;
    @PostMapping("/{id}")
    public ResponseEntity<GenericResponse<Event>> saveEvent(@Valid @RequestBody EventSaveRequest request,
                                                            @PathVariable String id) {
        return ResponseEntity.ok(GenericResponse.SUCCESS(
                this.eventService.saveEventRequestHandle(request,id)
        ));
    }
    @PutMapping("/")
    public ResponseEntity<GenericResponse<Void>> updateEvent(@JsonInclude(JsonInclude.Include.NON_NULL)
                                                                 @RequestBody
                                                                 EventSaveRequest request,@RequestParam String id){
        this.eventService.updateEventRequestHandle(request,id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> inActiveEvent(@RequestParam String id){
        this.eventService.eventInActiveHandle(id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @PostMapping("/addImagesByEvent")
    public ResponseEntity<GenericResponse<Void>> addImagesByEvent(@RequestParam String id, @RequestBody List<ImageAndLinks> imageAndLinks){
        this.eventService.addimages(id,imageAndLinks);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/")
    public ResponseEntity<GenericResponse<List<EventDto>>> findEvents(@RequestBody TableRequest tableRequest){
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.eventService.findEvents(tableRequest)));
    }

    @GetMapping("/findeventsbyid/{id}")
    public ResponseEntity<GenericResponse<EventDto>> findEventsById(@PathVariable String id){
        return ResponseEntity.ok(GenericResponse.SUCCESS(this.eventService.findEventById(id)));
    }

    @PostMapping("/registerEvent/{eventId}")
    public ResponseEntity<GenericResponse<Void>> registerEvent(@PathVariable String eventId, @RequestBody EventRegisterRequest request){
        this.eventService.eventRegister(eventId,request);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }



}
