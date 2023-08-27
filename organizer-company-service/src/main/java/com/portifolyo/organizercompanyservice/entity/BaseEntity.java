package com.portifolyo.organizercompanyservice.entity;

import jakarta.persistence.*;
import lombok.*;
import org.portifolyo.annotations.DontUpdate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @DontUpdate
    private String id;

    @Column(name = "created_time",updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_time",nullable = false)
    private LocalDateTime updatedDate;
    @Column(name = "is_active",nullable = false)
    private boolean active;


}
