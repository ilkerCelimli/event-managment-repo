package com.portifolyo.eventservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.eventservice.repository.projections.OrganizatorEventsInfos;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.OrganizatorService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/event-service/organizator")
@RequiredArgsConstructor
@SecurityRequirement(name = "Bearer Authentication")

public class OrganizatorApi {

    private final OrganizatorService organizatorService;


    @PutMapping("/")
    public ResponseEntity<GenericResponse<OrganizatorInfo>> updateOrganizator(
            @JsonInclude(JsonInclude.Include.NON_NULL)
            @RequestBody OrganizatorRequest request, @RequestParam String organizatorId) {
        OrganizatorInfo organizatorInfo = organizatorService.updateOrganizator(request, organizatorId);

        return ResponseEntity.ok(GenericResponse.SUCCESS(organizatorInfo));
    }

    @DeleteMapping("/")
    public ResponseEntity<GenericResponse<Void>> deleteOrganizator(@RequestParam String organizatorId) {
        this.organizatorService.deleteOrganizator(organizatorId);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @GetMapping("/findorganizatorbyemail/{email}")
    public ResponseEntity<GenericResponse<OrganizatorInfo>> findOrganizatorByEmail(@PathVariable String email){
        OrganizatorInfo result = this.organizatorService.findOrganizatorByEmail(email);
        return ResponseEntity.ok(GenericResponse.SUCCESS(result));
    }

    @GetMapping("/findorganizarevents/{email}")
    public ResponseEntity<GenericResponse<OrganizatorEventsInfos>> findOrganizatorEvents(@PathVariable String email, @RequestBody TableRequest request){

        return ResponseEntity.ok(GenericResponse.SUCCESS(this.organizatorService.findOrganizatorEvents(email,request)));
    }
}
