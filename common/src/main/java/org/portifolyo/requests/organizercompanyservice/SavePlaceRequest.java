package org.portifolyo.requests.organizercompanyservice;

import java.util.List;

public record SavePlaceRequest(
        String companyId,
        String name,
        int maxCapacity,
        int maxChair,
        boolean isBusy,
        AdressRequest adressRequest,
        List<InputEnum>placeInputs
) {
}
