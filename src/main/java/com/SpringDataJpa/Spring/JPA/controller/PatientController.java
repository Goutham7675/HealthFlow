package com.SpringDataJpa.Spring.JPA.controller;

import com.SpringDataJpa.Spring.JPA.dto.AppointmentResponseDto;
import com.SpringDataJpa.Spring.JPA.dto.CreateAppointmentRequestDto;
import com.SpringDataJpa.Spring.JPA.dto.PatientResponseDto;
import com.SpringDataJpa.Spring.JPA.service.AppointmentService;
import com.SpringDataJpa.Spring.JPA.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController {
    private final AppointmentService appointmentService;
    private final PatientService patientService;

    @PostMapping("/appointments")
    public ResponseEntity<AppointmentResponseDto> createAppointment(
            @RequestBody CreateAppointmentRequestDto createAppointmentRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(appointmentService.createNewAppointment(createAppointmentRequestDto));
    }

    @GetMapping("/appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointmentsOfPatient(
            @RequestParam(value = "patientId", defaultValue = "4") Long patientId) {
        return ResponseEntity.ok(appointmentService.getAllAppointmentsOfPatient(patientId));
    }

    @GetMapping("/all-appointments")
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/profile")
    private ResponseEntity<PatientResponseDto> getPatientProfile() {
        Long patientId = 4L;
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }
}
