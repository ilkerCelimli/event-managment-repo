package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "COMPANIES")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company extends BaseEntity {

    @Column(name = "company_name", nullable = false, length = 48)
    private String companyName;
    @Column(name = "tax_number", nullable = false)
    private String taxNumber;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Place> places = new HashSet<>();
    @OneToMany
    private Set<Adress> companyAdresses = new HashSet<>();
    @Column(name = "phone_number",length = 16)
    private String phoneNumber;




    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj
                || obj.hashCode() == this.hashCode()
                || obj instanceof Company
                || this.getId().equals(obj)) return true;
        return false;
    }
}


