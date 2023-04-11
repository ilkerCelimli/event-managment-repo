package org.portifolyo.requests.eventservice;

import org.portifolyo.requests.eventservice.enums.DescriptionTypes;

public record ImageAndLinksReqeust(
        String item,
        DescriptionTypes descriptionTypes
) {
}
