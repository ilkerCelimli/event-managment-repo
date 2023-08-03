package com.portifolyo.organizercompanyservice.util;

import com.portifolyo.organizercompanyservice.entity.Input;
import com.portifolyo.organizercompanyservice.entity.Place;
import org.portifolyo.requests.organizercompanyservice.SavePlaceRequest;

import java.util.HashSet;
import java.util.Set;


public class SavePlaceRequestMapper {

    private SavePlaceRequestMapper(){}
    public static Place toEntity(SavePlaceRequest savePlaceRequest) {
        Place place = new Place();
        place.setBusy(savePlaceRequest.isBusy());
        place.setName(savePlaceRequest.name());
        place.setMaxCapacity(savePlaceRequest.maxCapacity());
        place.setMaxChair(savePlaceRequest.maxChair());
        Set<Input> set =  new HashSet<>();
        savePlaceRequest.placeInputs().forEach(i -> {
            Input input = new Input();
            input.setInputs(i);
            set.add(input);
        });
        return place;
    }


}
