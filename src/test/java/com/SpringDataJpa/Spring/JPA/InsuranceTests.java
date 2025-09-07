package com.SpringDataJpa.Spring.JPA;

import com.SpringDataJpa.Spring.JPA.entity.Appointment;
import com.SpringDataJpa.Spring.JPA.entity.Insurance;
import com.SpringDataJpa.Spring.JPA.entity.Patient;
import com.SpringDataJpa.Spring.JPA.service.AppointmentService;
import com.SpringDataJpa.Spring.JPA.service.InsuranceService;
import com.SpringDataJpa.Spring.JPA.service.PatientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;

@SpringBootTest
public class InsuranceTests {
        @Autowired
        private InsuranceService insuranceService;

        @Autowired
        private AppointmentService appointmentService;
        @Autowired
        private PatientService patientService;

        @Test
        public void testInsurance() {
                // Use a unique policy number to avoid constraint violation
                String uniquePolicyNumber = "HDFC_" + System.currentTimeMillis();

                Insurance insurance = Insurance.builder().policyNumber(uniquePolicyNumber).provider("HDFC")
                                .validTill(LocalDate.of(2030, 12, 12)).build();
                Patient patient = insuranceService.assignInsuranceToPatient(insurance, 1L);
                System.out.println(patient);

                var newPatient = insuranceService.disassociateInsuranceFromPatient(patient.getId());
                System.out.println(newPatient);
        }

        @Test
        public void testAppointment() {
                // Create a DTO for the appointment request
                var appointmentRequest = new com.SpringDataJpa.Spring.JPA.dto.CreateAppointmentRequestDto();
                appointmentRequest.setDoctorId(1L);
                appointmentRequest.setPatientId(1L);
                appointmentRequest.setAppointmentTime(LocalDateTime.of(2025, 11, 1, 14, 0));
                appointmentRequest.setReason("Cough");
                
                var newAppointment = appointmentService.createNewAppointment(appointmentRequest);
                System.out.println(newAppointment);

                var updatedAppointment = appointmentService.reAssignAppointmentToAnotherDoctor(newAppointment.getId(),
                                2L);
                System.out.println("Appointment reassigned: " + updatedAppointment.getId() + " to Doctor: "
                                + updatedAppointment.getDoctor().getId());
        }
}
