package com.codelegends.ecommerce.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter
public abstract class BaseClass {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(nullable=false)
    private boolean isActive=true;
    @Column(nullable=false,updatable=false)
    private LocalDateTime createdDate;
    @Column(nullable=false)
    private LocalDateTime updatedDate;
    @PrePersist void prePersist() {
        createdDate=updatedDate=LocalDateTime.now();
        isActive=true;
    }
    @PreUpdate void preUpdate() {
        updatedDate=LocalDateTime.now();
    }
}
