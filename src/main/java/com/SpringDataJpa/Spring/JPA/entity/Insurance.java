package com.SpringDataJpa.Spring.JPA.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String policyNumber;
    private String provider;
    private String type;
    private Double coverageAmount;
    private LocalDate validTill;
    
    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime createdAt;
}
