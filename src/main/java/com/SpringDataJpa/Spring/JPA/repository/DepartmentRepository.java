package com.SpringDataJpa.Spring.JPA.repository;

import com.SpringDataJpa.Spring.JPA.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}