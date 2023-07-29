package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.service.annotation.GetExchange;

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
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
        if(this == obj) return true;
        if(obj.hashCode() == this.hashCode()) return true;
        if(obj instanceof Adress) return true;
        if(this.getId().equals(obj)) return true;
        return false;
    }
}
