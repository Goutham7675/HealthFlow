package com.SpringDataJpa.Spring.JPA.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime appointmentTime;

    @Column(length = 50)
    private String reason;

//    cascadiing should be done only on the parent side as parent decides the lifecycle of child
    @ManyToOne //(cascade = CascadeType.ALL) // a patient can have many appointments
    @JoinColumn(name = "patient_id",nullable = false)
    @ToString.Exclude
    private Patient patient; // Owning side

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    @ToString.Exclude
    private Doctor doctor;
}
