package com.SpringDataJpa.Spring.JPA.repository;

import com.SpringDataJpa.Spring.JPA.dto.BloodGroupCountResponseEntity;
import com.SpringDataJpa.Spring.JPA.entity.Patient;
import com.SpringDataJpa.Spring.JPA.entity.type.BloodGroupType;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Long> {

	Patient findByName(String name);

	List<Patient> findAllByName(String name);

	Patient findFirstByName(String name);

	@Query("select p from Patient p where p.birthDate = :birthDate")
	Patient findByBirthDate(@Param("birthDate") LocalDate birthDate);

	List<Patient> findByBirthDateOrEmail(LocalDate birthDate, String email);
	List<Patient> findByNameContainingOrderByIdDesc(String name);

	@Query("select p from Patient p where p.bloodGroup = ?1")
	List<Patient> findByBloodGroup(BloodGroupType bloodGroup);

	@Query("select p from Patient p where p.birthDate > :birthDate")
	List<Patient> findByBornDateAfter(@Param("birthDate") LocalDate birthDate);

//	@Query("select p.bloodGroup, count(p) from Patient p group by p.bloodGroup")
//	List<Object[]> countByBloodGroup(); // @Param("bloodGroup") BloodGroupType bloodGroup

	@Query("select p.bloodGroup, count(p) from Patient p group by p.bloodGroup")
	List<Object[]> countByBloodGroup();


//	@Query(value = "select * from patient", nativeQuery = true)
//	List<Patient> findAllPatients();


	@Query(value = "select * from patient", nativeQuery = true)
	Page<Patient> findAllPatients(Pageable pageable);

	@Transactional
	@Modifying
	@Query("UPDATE Patient p SET p.name = :name where p.id = :id")
	int updateNameWithId(@Param("name") String name, @Param("id") Long id);

//	@Query("SELECT p FROM Patient p LEFT JOIN FETCH p.appointments a LEFT JOIN fetch a.doctor")
//	@Query("SELECT DISTINCT p FROM Patient p JOIN FETCH p.appointments a LEFT JOIN FETCH a.doctor")
	@Query("SELECT DISTINCT p FROM Patient p JOIN FETCH p.appointments")

	List<Patient> findPatientsWithAppointments();

}
