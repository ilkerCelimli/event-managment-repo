package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Table(name = "adress")
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@Setter
public class Adress extends BaseEntity {
    @Column(name = "open_adress",length = 100)
    private String openAdress;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "city_id")
    private City city;


    @Override
    public String toString(){
        return this.getId();
    }

    @Override
    public boolean equals(Object obj){
        return  obj != null && (this == obj
                || obj.hashCode() == this.hashCode()
                ||obj instanceof Adress
                || this.getId().equals(obj));
    }

    @Override
    public int hashCode(){
       return Objects.hash(this.getId());
    }

}
