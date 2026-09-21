package com.learning.accounts.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter @ToString
public class BaseEntity {

    @Column( updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @Column(updatable = false)
    private String createdBy;
    private String updatedBy;

}
