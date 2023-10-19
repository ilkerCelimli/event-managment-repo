package org.portifolyo.requests.eventservice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.portifolyo.requests.eventservice.enums.DescriptionTypes;

import java.io.Serial;
import java.io.Serializable;

public record ImageAndLinksReqeust(
       @NotNull
       @NotBlank
       String item,
       DescriptionTypes descriptionTypes
) implements Serializable {
}
