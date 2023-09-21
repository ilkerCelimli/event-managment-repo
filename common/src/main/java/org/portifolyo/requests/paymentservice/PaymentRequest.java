package org.portifolyo.requests.paymentservice;

import java.math.BigDecimal;

public record PaymentRequest(
        String name,
        String surname,
        String cardFirstSixNumber,
        String cardLastSixNumber,
        BigDecimal total,
        String userId,
        String companyId
) {

}
