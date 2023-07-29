package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "COMPANIES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company extends BaseEntity {

    private String companyName;
    private String taxNumber;
    private String userId;


    @Override
    public String toString(){
        return this.getId();
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj.hashCode() == this.hashCode()) return true;
        if(obj instanceof Company) return true;
        if(this.getId().equals(obj)) return true;
        return false;
        }
    }


