package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portifolyo.requests.organizercompanyservice.InputEnum;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "input")
@Inheritance(strategy = InheritanceType.JOINED)

public class Input extends BaseEntity {

    private InputEnum inputs;
    @ManyToOne
    @JoinColumn(name = "place_id")
    private Place place;


    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null && (this == obj
                || obj.hashCode() == this.hashCode()
                || obj instanceof Input
                || this.getId().equals(obj));
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.getId());
    }
}
