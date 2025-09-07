package com.SpringDataJpa.Spring.JPA.service;

import com.SpringDataJpa.Spring.JPA.entity.Insurance;
import com.SpringDataJpa.Spring.JPA.entity.Patient;
import com.SpringDataJpa.Spring.JPA.repository.InsuranceRepository;
import com.SpringDataJpa.Spring.JPA.repository.PatientRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor

public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;

    @Transactional
    public Patient assignInsuranceToPatient(Insurance insurance, Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new EntityNotFoundException("Patient not found with id: " + patientId));
        patient.setInsurance(insurance);
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient disassociateInsuranceFromPatient(Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        patient.setInsurance(null);
        return patient;
    }
}
