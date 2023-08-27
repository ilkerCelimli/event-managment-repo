package com.portifolyo.organizercompanyservice.api;

import com.portifolyo.organizercompanyservice.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.portifolyo.requests.organizercompanyservice.SavePlaceRequest;
import org.portifolyo.response.GenericResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company-service/place")
@RequiredArgsConstructor
public class PlaceApi {

    private final PlacesService placesService;


    @PostMapping
    public ResponseEntity<GenericResponse<Void>> savePlace(@RequestBody SavePlaceRequest savePlaceRequest){
        this.placesService.handlePlaceRequest(savePlaceRequest);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @PutMapping("/{id}")
    public ResponseEntity<GenericResponse<Void>> updatePlace(@RequestBody SavePlaceRequest savePlaceRequest,
                                                             @PathVariable String id){
        this.placesService.updatePlaceHandler(savePlaceRequest,id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }

    @PutMapping("/update-place-status/{id}")
    public ResponseEntity<GenericResponse<Void>> updatePlaceStatus(@PathVariable String id,@RequestParam boolean status){
        this.placesService.updatePlaceBusyStatus(status,id);
        return ResponseEntity.ok(GenericResponse.SUCCESS());
    }
}
