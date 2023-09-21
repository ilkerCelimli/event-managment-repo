package org.portifolyo.requests.eventservice;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import org.portifolyo.requests.eventservice.enums.EventType;

import java.time.LocalDateTime;
import java.util.List;

import static org.portifolyo.requests.eventservice.messages.EventSaveRequestMessage.*;

public record EventSaveRequest(
        List<OrganizatorRequest> organizatorLists,
        @NotNull(message = EVENT_NAME_NOT_NULL)
        String eventName,
        @NotNull(message = EVENT_DATE_NOT_NULL)
        LocalDateTime eventDate,
        @Min(value = 0,message = COMING_PEOPLE_NOT_NEGATIVE)
        Integer comingPeople,

        Boolean isTicket,
        Boolean isPeopleIsRegistered,
        @NotNull(message = EVENT_TYPE_NOT_NULL)
        EventType eventType,
        Integer maxPeople,
        @NotNull(message = EVENT_AREA_IS_NOT_NULL)
        @Valid
        EventAreaRequest eventAreaRequest,
        @Valid
        EventDescriptionRequest description
) {
}
