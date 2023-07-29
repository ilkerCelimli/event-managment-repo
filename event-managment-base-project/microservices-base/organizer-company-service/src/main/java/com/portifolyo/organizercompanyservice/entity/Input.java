package com.portifolyo.organizercompanyservice.entity;

import com.portifolyo.organizercompanyservice.enums.Inputs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "INPUT")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)

public class Input extends BaseEntity {

    private Inputs inputs;
    @ManyToOne
    @JoinColumn(name = "place_id")
    Place place;

}
