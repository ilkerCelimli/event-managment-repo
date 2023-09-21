package org.portifolyo.requests.eventservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.portifolyo.requests.eventservice.enums.DescriptionTypes;

public record ImageAndLinksReqeust(
       @NotNull
       @NotBlank
       String item,
       DescriptionTypes descriptionTypes
) {
}
