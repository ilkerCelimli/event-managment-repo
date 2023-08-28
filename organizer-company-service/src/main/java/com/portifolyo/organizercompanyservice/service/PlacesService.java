package com.portifolyo.organizercompanyservice.service;

import com.portifolyo.organizercompanyservice.entity.Place;
import com.portifolyo.organizercompanyservice.repository.projections.PlaceInfo;
import org.portifolyo.requests.TableRequest;
import org.portifolyo.requests.organizercompanyservice.SavePlaceRequest;

import java.util.List;

public interface PlacesService extends BaseService<Place> {

    void handlePlaceRequest(SavePlaceRequest savePlaceRequest);

    void updatePlaceBusyStatus(boolean update,String placeId);

    void updatePlaceHandler(SavePlaceRequest savePlaceRequest,String id);

    List<PlaceInfo> findPlacesByCompanyId(TableRequest tableRequest, String id);
}
