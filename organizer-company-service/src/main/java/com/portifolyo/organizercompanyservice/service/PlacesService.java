package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.Place;
import org.portifolyo.requests.organizercompanyservice.SavePlaceRequest;

public interface PlacesService extends BaseService<Place> {

    void handlePlaceRequest(SavePlaceRequest savePlaceRequest);

    void updatePlaceBusyStatus(boolean update,String placeId);
}
