package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.*;

@Entity
@Table(name = "companies",indexes = {
        @Index(name = "idx_company_name",columnList = "company_name"),
        @Index(name = "idx_company_tax_number",columnList = "tax_number"),
})
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
    @Column(name = "phone_number",length = 16,nullable = false)
    private String phoneNumber;
    @Column(name = "company_super_admin_user_id",nullable = false)
    private String companySuperAdminUserId;
    @OneToMany(mappedBy = "company", fetch = FetchType.EAGER)
    private Set<Place> places = new HashSet<>();
    @OneToMany
    private List<Adress> companyAdresses = new ArrayList<>();





    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
       return  obj != null && (this == obj
                || obj.hashCode() == this.hashCode()
                || obj instanceof Company
                || this.getId().equals(obj));
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.getId());
    }

}


