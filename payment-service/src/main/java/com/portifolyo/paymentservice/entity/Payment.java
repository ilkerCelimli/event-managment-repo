package com.portifolyo.paymentservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
@Entity
@Table(name = "payment")
public class Payment {

    @GeneratedValue
    @UuidGenerator
    @Id
    private String id;

    @Column(name = "name", length = 32)
    private String name;
    @Column(name = "surname", length = 32)
    private String surname;
    @Column(name = "card_first_six_number", length = 6)
    private String cardFirstSixNumber;
    @Column(name = "card_last_six_number", length = 6)
    private String cardLastSixNumber;
    @Column(name = "total_payment")
    private BigDecimal total;
    @Column(name = "user_id", length = 128)
    private String userId;
    @Column(name = "paid_company_id",length = 128)
    private String paidCompanyId;
    @Column(name = "commusion_rate")
    private BigDecimal commuisionRate;
    @Column(name = "amount_paid")
    private BigDecimal amountPaid;

}
