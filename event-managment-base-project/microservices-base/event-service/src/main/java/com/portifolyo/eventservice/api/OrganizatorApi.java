package com.portifolyo.eventservice.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.portifolyo.eventservice.repository.projections.OrganizatorInfo;
import com.portifolyo.eventservice.service.EventService;
import com.portifolyo.eventservice.service.OrganizatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.eventservice.OrganizatorRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/organizator")
@RequiredArgsConstructor
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
}
