package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.portifolyo.requests.organizercompanyservice.InputEnum;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity(name = "place")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class Place extends BaseEntity {

    private String name;
    @OneToOne
    @JoinColumn(name = "adress_id")
    private Adress adress;
    @Column(name = "max_capacity",nullable = false)
    private Integer maxCapacity;
    @Column(name = "max_chair",nullable = false)
    private Integer maxChair;
    @Column(name = "is_busy")
    private boolean isBusy;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Input> inputs = new HashSet<>();

    @Override
    public String toString() {
        return this.getId();
    }

    @Override
    public boolean equals(Object obj) {
        return obj != null &&(this == obj
                || obj.hashCode() == this.hashCode()
                || obj instanceof Place
                || this.getId().equals(obj));
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.getId());
    }
}
