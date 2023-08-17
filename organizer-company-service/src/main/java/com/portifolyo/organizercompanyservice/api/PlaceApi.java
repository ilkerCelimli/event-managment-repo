package com.portifolyo.organizercompanyservice.api;

import com.portifolyo.organizercompanyservice.service.PlacesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class PlaceApi {

    private final PlacesService placesService;


}
