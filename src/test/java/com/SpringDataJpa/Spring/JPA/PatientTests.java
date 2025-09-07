package com.SpringDataJpa.Spring.JPA;

import com.SpringDataJpa.Spring.JPA.dto.BloodGroupCountResponseEntity;
import com.SpringDataJpa.Spring.JPA.entity.Patient;
import com.SpringDataJpa.Spring.JPA.entity.Insurance;
import com.SpringDataJpa.Spring.JPA.entity.Appointment;
import com.SpringDataJpa.Spring.JPA.entity.type.BloodGroupType;
import com.SpringDataJpa.Spring.JPA.repository.PatientRepository;
import com.SpringDataJpa.Spring.JPA.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PatientTests {
	@Autowired
	private PatientRepository patientRepository;
	@Autowired
	private PatientService patientService;

	@Test
	public void testPatientRepository() {
		// Fetch directly from existing DB without inserting
		// List<Patient> patientsList = patientRepository.findAll();
		// List<Patient> patientsList =
		// patientRepository.findAllPatientsWithAppointments();
		List<Patient> patientsList = patientRepository.findPatientsWithAppointments();
		System.out.println(patientsList);
		// assertNotNull(patient, "Expected an existing patient named 'Ananya Sharma' in
		// DB");
		//
		// Test with a patient that has insurance
		// Patient patientWithInsurance = patientRepository.findFirstByName("Sai Goutham
		// Reddy");
		// if (patientWithInsurance != null) {
		// System.out.println("Patient: " + patientWithInsurance);
		// if (patientWithInsurance.getInsurance() != null) {
		// System.out.println("Insurance Details:");
		// System.out.println(" Policy Number: " +
		// patientWithInsurance.getInsurance().getPolicyNumber());
		// System.out.println(" Provider: " +
		// patientWithInsurance.getInsurance().getProvider());
		// System.out.println(" Valid Till: " +
		// patientWithInsurance.getInsurance().getValidTill());
		// System.out.println(" Created At: " +
		// patientWithInsurance.getInsurance().getCreatedAt());
		// } else {
		// System.out.println("No insurance found for this patient");
		// }
		// }
	}

	@Test
	public void testTransactionMethods() {
		// Fetch by name (duplicates allowed) and by id via service
		// List<Patient> patientsByName = patientRepository.findAllByName("Sai
		// Goutham");
		// assertNotNull(patientsByName);
		// assertTrue(patientsByName.size() >= 1, "Expected at least one person with the
		// name given");
		//
		// Patient first = patientsByName.get(0);
		// Patient byId = patientService.getPatientById(first.getId());
		// assertNotNull(byId);
		// assertEquals(first.getName(), byId.getName());\
		// List<Patient> patients =
		// patientRepository.findByBirthDateOrEmail(LocalDate.of(2000, 5, 15),
		// "arjun@example.com");
		// List<Patient> patients =
		// patientRepository.findByNameContainingOrderByIdDesc("Reddy");
		// List<Patient> patients =
		// patientRepository.findByBloodGroup(BloodGroupType.A_POSITIVE);
		//
		// List<Patient> patients = patientRepository.findAllPatients();
		// for(Patient patient : patients) {
		// System.out.println(patient);
		// }
		//
		// List<Object[]> bloodGroupList = patientRepository.countByBloodGroup();
		// for(Object[] bloodGroup : bloodGroupList) {
		// System.out.println(bloodGroup[0] + " " + bloodGroup[1]);
		// }

		// int updatedRow = patientRepository.updateNameWithId("Goutham Reddy", 2L);
		// System.out.println("Updated Row: " + updatedRow);

		// List<BloodGroupCountResponseEntity> bloodGroupList =
		// patientRepository.countByBloodGroup();
		// for(BloodGroupCountResponseEntity bloodGroup: bloodGroupList) {
		// System.out.println(bloodGroup);
		// }

		Page<Patient> patients = patientRepository.findAllPatients(PageRequest.of(0, 2, Sort.by("name")));
		for (Patient patient : patients) {
			System.out.println(patient);
		}
	}

	@Test
	public void testPatientWithInsuranceDetails() {
		// Test fetching a patient and displaying insurance details
		Patient patient = patientRepository.findFirstByName("Sai Goutham Reddy");
		if (patient != null) {
			System.out.println("=== Patient Information ===");
			System.out.println("ID: " + patient.getId());
			System.out.println("Name: " + patient.getName());
			System.out.println("Birth Date: " + patient.getBirthDate());
			System.out.println("Email: " + patient.getEmail());
			System.out.println("Gender: " + patient.getGender());
			System.out.println("Blood Group: " + patient.getBloodGroup());
			System.out.println("Created At: " + patient.getCreatedAt());

			System.out.println("\n=== Insurance Information ===");
			if (patient.getInsurance() != null) {
				Insurance insurance = patient.getInsurance();
				System.out.println("Policy Number: " + insurance.getPolicyNumber());
				System.out.println("Provider: " + insurance.getProvider());
				System.out.println("Valid Till: " + insurance.getValidTill());
				System.out.println("Created At: " + insurance.getCreatedAt());
			} else {
				System.out.println("No insurance information available");
			}
		} else {
			System.out.println("Patient 'Sai Goutham Reddy' not found");
		}
	}

	@Test
	public void testAppointmentWithPatientDetails() {
		// Test to show that Appointment.toString() now includes patient information
		Patient patient = patientRepository.findFirstByName("Sai Goutham Reddy");
		if (patient != null && !patient.getAppointments().isEmpty()) {
			System.out.println("=== Testing Appointment with Patient Details ===");
			Appointment appointment = patient.getAppointments().get(0);
			System.out.println("Appointment: " + appointment);
			System.out.println(
					"\nThis should show appointment details + patient info (but not appointments list to avoid circular ref)");
		} else {
			System.out.println("No appointments found for patient 'Sai Goutham Reddy'");
		}
	}

}
