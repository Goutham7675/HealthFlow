package com.SpringDataJpa.Spring.JPA.entity;


import jakarta.persistence.*;
import lombok.*;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.*;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @MapsId
    private User user;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(length = 100)
    private String specialization;

    @Column(nullable = true, unique = true, length = 100)
    private String email;

    @ManyToMany(mappedBy = "doctors")
    @ToString.Exclude
    private Set<Department> departments= new HashSet<>();

    @OneToMany(mappedBy = "doctor")
    @ToString.Exclude
    private List<Appointment> appointments = new ArrayList<>();


}
