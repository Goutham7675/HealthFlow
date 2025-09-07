package com.SpringDataJpa.Spring.JPA.repository;

import com.SpringDataJpa.Spring.JPA.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}