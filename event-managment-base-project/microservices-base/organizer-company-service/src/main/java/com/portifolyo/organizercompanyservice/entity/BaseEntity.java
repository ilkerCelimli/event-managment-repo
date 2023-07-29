package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "created_time",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_time",nullable = false)
    private LocalDateTime updatedDate;
    @Column(name = "is_active",nullable = false)
    private boolean active;


}
