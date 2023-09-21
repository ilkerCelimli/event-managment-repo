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

   @Column(name = "name",length = 32 )
   private String name;
   @Column(name = "surname",length = 32)
   private String surname;
   @Column(name = "card_first_six_number",length = 6)
   private String cardFirstSixNumber;
   @Column(name = "card_last_six_number",length = 6)
   private String cardLastSixNumber;
   @Column(name = "total")
   private BigDecimal total;

}
