package com.portifolyo.paymentservice.repository;

import java.math.BigDecimal;

/**
 * Projection for {@link com.portifolyo.paymentservice.entity.Payment}
 */
public interface PaymentInfo {
    String getId();

    String getName();

    String getSurname();

    BigDecimal getTotal();

    String getUserId();

    String getPaidCompanyId();

    BigDecimal getCommuisionRate();

    BigDecimal getAmountPaid();

    Boolean isIsDeleted();
}