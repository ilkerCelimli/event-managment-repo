package org.portifolyo.requests.eventservice;

import java.util.List;

public record EventDescriptionRequest(
    String description,
    List<ImageAndLinksReqeust> imageAndLinksReqeusts
) {
}
