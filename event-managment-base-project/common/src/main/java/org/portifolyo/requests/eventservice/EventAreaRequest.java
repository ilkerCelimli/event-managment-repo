package org.portifolyo.requests.eventservice;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import static org.portifolyo.requests.eventservice.messages.EventAreaRequestMessages.*;

public record EventAreaRequest(
    @NotNull(message = AREA_NAME_NOT_NULL)
     String area_name,
    @NotNull(message = AREA_LAT_IS_NOT_NULL)
    @Min(value = 0,message = AREA_LAT_IS_NOT_NEGATIVE)
    @Max(value = 181, message = AREA_LAT_IS_RANGE)
     String lat,
    @NotNull(message = AREA_LNG_IS_NOT_NULL)
    @Max(value = 181,message = AREA_LNG_IS_RANGE)
    @Min(value = 0,message = AREA_LNG_IS_NOT_NEGATIVE)
     String lng,
    @NotNull @Min(value = 0,message =AREA_CAPACITY_NOT_NEGATIVE )
     Integer areaCapacity,
    @NotNull(message = OPEN_ADRESS_NOT_NULL)
    @NotBlank(message = OPEN_ADRESS_IS_NOT_BLANK)
     String openAdress
) {





}
