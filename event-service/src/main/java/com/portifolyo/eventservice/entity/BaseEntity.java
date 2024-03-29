package com.portifolyo.eventservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.portifolyo.annotations.DontUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @DontUpdate
    private String id;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date",nullable = false)
    @DontUpdate
    private LocalDateTime createdDate;
    @Column(name = "is_deleted")

    private Boolean deleted = false;



}
