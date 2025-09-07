package com.SpringDataJpa.Spring.JPA.repository;

import com.SpringDataJpa.Spring.JPA.entity.Insurance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
}