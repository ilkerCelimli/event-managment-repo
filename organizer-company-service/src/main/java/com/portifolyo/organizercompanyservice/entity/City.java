package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity(name = "CITIES")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class City extends BaseEntity {

    private String cityName;
    private String cityCode;
    @Override
    public String toString(){
        return this.getId();
    }

    @Override
    public boolean equals(Object obj){
        return  obj != null &&(this == obj
                || obj.hashCode() == this.hashCode()
                ||obj instanceof City
                || this.getId().equals(obj));
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityName, cityCode);
    }
}
