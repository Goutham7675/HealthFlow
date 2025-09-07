package com.SpringDataJpa.Spring.JPA.entity;

import com.SpringDataJpa.Spring.JPA.entity.type.BloodGroupType;
import com.SpringDataJpa.Spring.JPA.entity.Appointment;
import com.SpringDataJpa.Spring.JPA.entity.Insurance;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
//@RequiredArgsConstructor
@Table(name = "patient")
@Builder
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(name = "dob")
	private LocalDate birthDate;

	@Column(unique = true, nullable = false)
	private String email;

	private String gender;

	@OneToOne
	@MapsId
	private User user;

	@CreationTimestamp
	@Column(updatable = false)
	private LocalDateTime createdAt;

	// Converter will handle mapping automatically
	@Enumerated(EnumType.STRING)
	private BloodGroupType bloodGroup;

	@OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true)
	@JoinColumn(name = "patient_insurance_id")
	private Insurance insurance; // Owning Side


	// Inverse side a patient can've many appointments
	@OneToMany(mappedBy = "patient", fetch = FetchType.EAGER,
			cascade = CascadeType.REMOVE, orphanRemoval = true)
//	@ToString.Exclude
	private List<Appointment> appointments = new ArrayList<>();


}
