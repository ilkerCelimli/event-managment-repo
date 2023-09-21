package org.portifolyo.response;

import java.util.Date;

public record PaymentResponse(
        String id,
        String name,
        String surname,
        Date time,
        double paid

) {
}
