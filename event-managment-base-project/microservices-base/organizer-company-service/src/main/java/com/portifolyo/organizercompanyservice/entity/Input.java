package com.portifolyo.organizercompanyservice.entity;

import com.portifolyo.organizercompanyservice.enums.Inputs;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

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


    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&(this == obj
                || obj.hashCode() == this.hashCode()
                || obj instanceof Input
                || this.getId().equals(obj));
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getId());
    }
}