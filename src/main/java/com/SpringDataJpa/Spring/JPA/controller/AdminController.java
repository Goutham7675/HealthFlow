package com.SpringDataJpa.Spring.JPA.controller;

import com.SpringDataJpa.Spring.JPA.dto.DoctorResponseDto;
import com.SpringDataJpa.Spring.JPA.dto.OnboardDoctorRequestDto;
import com.SpringDataJpa.Spring.JPA.dto.PatientResponseDto;
import com.SpringDataJpa.Spring.JPA.entity.User;
import com.SpringDataJpa.Spring.JPA.service.PatientService;
import com.SpringDataJpa.Spring.JPA.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final PatientService patientService;
    private final DoctorService doctorService;

    @GetMapping("/patients")
    public ResponseEntity<List<PatientResponseDto>> getAllPatients(
            @RequestParam(value = "page", defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize)
    {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(patientService.getAllPatients(pageNumber, pageSize));
    }

    @PostMapping("/onBoardNewDoctor")
    public ResponseEntity<DoctorResponseDto> onBoardNewDoctor(@RequestBody OnboardDoctorRequestDto onboardDoctorRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(doctorService.onBoardNewDoctor(onboardDoctorRequestDto));
    }
    @GetMapping("/doctors")
    public ResponseEntity<?> getAllDoctorsForAdmin() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }
}
