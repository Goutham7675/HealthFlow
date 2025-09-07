package com.SpringDataJpa.Spring.JPA.controller;

import com.SpringDataJpa.Spring.JPA.dto.LoginRequestDto;
import com.SpringDataJpa.Spring.JPA.dto.LoginResponseDto;
import com.SpringDataJpa.Spring.JPA.dto.SignUpRequestDto;
import com.SpringDataJpa.Spring.JPA.dto.SignupResponseDto;
import com.SpringDataJpa.Spring.JPA.dto.AppointmentResponseDto;
import com.SpringDataJpa.Spring.JPA.service.AppointmentService;
import com.SpringDataJpa.Spring.JPA.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final AppointmentService appointmentService;

    @PostMapping({"/login", "/auth/login"})
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(authService.login(loginRequestDto));
    }

    @PostMapping({"/signup", "/auth/signup"})
    public ResponseEntity<SignupResponseDto> signup(@RequestBody SignUpRequestDto signupRequestDto) {
        return ResponseEntity.ok(authService.signup(signupRequestDto));
    }

    @GetMapping({"/doctors/appointments", "/auth/doctors/appointments"})
    public ResponseEntity<List<AppointmentResponseDto>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }
}
